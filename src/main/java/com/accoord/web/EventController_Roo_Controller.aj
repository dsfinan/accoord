// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.web;

import com.accoord.domain.AssessmentArea;
import com.accoord.domain.Event;
import com.accoord.domain.Message;
import com.accoord.domain.SecurityIncident;
import com.accoord.domain.ServiceArea;
import com.accoord.domain.UserEntity;
import com.accoord.shared.EventType;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect EventController_Roo_Controller {
    
    @Autowired
    private GenericConversionService EventController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String EventController.create(@Valid Event event, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            addDateTimeFormatPatterns(model);
            return "events/create";
        }
        event.persist();
        return "redirect:/events/" + encodeUrlPathSegment(event.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String EventController.createForm(Model model) {
        model.addAttribute("event", new Event());
        addDateTimeFormatPatterns(model);
        List dependencies = new ArrayList();
        if (UserEntity.countUserEntitys() == 0) {
            dependencies.add(new String[]{"owner", "userentitys"});
        }
        model.addAttribute("dependencies", dependencies);
        return "events/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String EventController.show(@PathVariable("id") Long id, Model model) {
        addDateTimeFormatPatterns(model);
        model.addAttribute("event", Event.findEvent(id));
        model.addAttribute("itemId", id);
        return "events/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String EventController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("events", Event.findEventEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Event.countEvents() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("events", Event.findAllEvents());
        }
        addDateTimeFormatPatterns(model);
        return "events/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String EventController.update(@Valid Event event, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            addDateTimeFormatPatterns(model);
            return "events/update";
        }
        event.merge();
        return "redirect:/events/" + encodeUrlPathSegment(event.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String EventController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", Event.findEvent(id));
        addDateTimeFormatPatterns(model);
        return "events/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String EventController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Event.findEvent(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/events?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("assessmentareas")
    public Collection<AssessmentArea> EventController.populateAssessmentAreas() {
        return AssessmentArea.findAllAssessmentAreas();
    }
    
    @ModelAttribute("messages")
    public Collection<Message> EventController.populateMessages() {
        return Message.findAllMessages();
    }
    
    @ModelAttribute("securityincidents")
    public Collection<SecurityIncident> EventController.populateSecurityIncidents() {
        return SecurityIncident.findAllSecurityIncidents();
    }
    
    @ModelAttribute("serviceareas")
    public Collection<ServiceArea> EventController.populateServiceAreas() {
        return ServiceArea.findAllServiceAreas();
    }
    
    @ModelAttribute("userentitys")
    public Collection<UserEntity> EventController.populateUserEntitys() {
        return UserEntity.findAllUserEntitys();
    }
    
    @ModelAttribute("eventtypes")
    public Collection<EventType> EventController.populateEventTypes() {
        return Arrays.asList(EventType.class.getEnumConstants());
    }
    
    Converter<AssessmentArea, String> EventController.getAssessmentAreaConverter() {
        return new Converter<AssessmentArea, String>() {
            public String convert(AssessmentArea assessmentArea) {
                return new StringBuilder().append(assessmentArea.getName()).append(" ").append(assessmentArea.getDescription()).append(" ").append(assessmentArea.getCreated()).toString();
            }
        };
    }
    
    Converter<Event, String> EventController.getEventConverter() {
        return new Converter<Event, String>() {
            public String convert(Event event) {
                return new StringBuilder().append(event.getName()).append(" ").append(event.getDescription()).append(" ").append(event.getCountry()).toString();
            }
        };
    }
    
    Converter<Message, String> EventController.getMessageConverter() {
        return new Converter<Message, String>() {
            public String convert(Message message) {
                return new StringBuilder().append(message.getMessage()).append(" ").append(message.getCreated()).toString();
            }
        };
    }
    
    Converter<SecurityIncident, String> EventController.getSecurityIncidentConverter() {
        return new Converter<SecurityIncident, String>() {
            public String convert(SecurityIncident securityIncident) {
                return new StringBuilder().append(securityIncident.getIncidentdate()).append(" ").append(securityIncident.getSubject()).append(" ").append(securityIncident.getDescription()).toString();
            }
        };
    }
    
    Converter<ServiceArea, String> EventController.getServiceAreaConverter() {
        return new Converter<ServiceArea, String>() {
            public String convert(ServiceArea serviceArea) {
                return new StringBuilder().append(serviceArea.getName()).append(" ").append(serviceArea.getDescription()).append(" ").append(serviceArea.getCreated()).toString();
            }
        };
    }
    
    Converter<UserEntity, String> EventController.getUserEntityConverter() {
        return new Converter<UserEntity, String>() {
            public String convert(UserEntity userEntity) {
                return new StringBuilder().append(userEntity.getUsername()).append(" ").append(userEntity.getPassword()).append(" ").append(userEntity.getEmail()).toString();
            }
        };
    }
    
    @PostConstruct
    void EventController.registerConverters() {
        conversionService.addConverter(getAssessmentAreaConverter());
        conversionService.addConverter(getEventConverter());
        conversionService.addConverter(getMessageConverter());
        conversionService.addConverter(getSecurityIncidentConverter());
        conversionService.addConverter(getServiceAreaConverter());
        conversionService.addConverter(getUserEntityConverter());
    }
    
    void EventController.addDateTimeFormatPatterns(Model model) {
        model.addAttribute("event_dateclosed_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        model.addAttribute("event_created_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    private String EventController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}