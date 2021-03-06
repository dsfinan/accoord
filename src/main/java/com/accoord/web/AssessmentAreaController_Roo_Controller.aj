// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.web;

import com.accoord.domain.AssessmentArea;
import com.accoord.domain.Event;
import com.accoord.domain.Message;
import com.accoord.domain.RapidAssessment;
import com.accoord.domain.UserEntity;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
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

privileged aspect AssessmentAreaController_Roo_Controller {
    
    @Autowired
    private GenericConversionService AssessmentAreaController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String AssessmentAreaController.create(@Valid AssessmentArea assessmentArea, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("assessmentArea", assessmentArea);
            addDateTimeFormatPatterns(model);
            return "assessmentareas/create";
        }
        assessmentArea.persist();
        return "redirect:/assessmentareas/" + encodeUrlPathSegment(assessmentArea.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String AssessmentAreaController.createForm(Model model) {
        model.addAttribute("assessmentArea", new AssessmentArea());
        addDateTimeFormatPatterns(model);
        List dependencies = new ArrayList();
        if (Event.countEvents() == 0) {
            dependencies.add(new String[]{"event", "events"});
        }
        if (UserEntity.countUserEntitys() == 0) {
            dependencies.add(new String[]{"owner", "userentitys"});
        }
        model.addAttribute("dependencies", dependencies);
        return "assessmentareas/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String AssessmentAreaController.show(@PathVariable("id") Long id, Model model) {
        addDateTimeFormatPatterns(model);
        model.addAttribute("assessmentarea", AssessmentArea.findAssessmentArea(id));
        model.addAttribute("itemId", id);
        return "assessmentareas/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String AssessmentAreaController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("assessmentareas", AssessmentArea.findAssessmentAreaEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) AssessmentArea.countAssessmentAreas() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("assessmentareas", AssessmentArea.findAllAssessmentAreas());
        }
        addDateTimeFormatPatterns(model);
        return "assessmentareas/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String AssessmentAreaController.update(@Valid AssessmentArea assessmentArea, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("assessmentArea", assessmentArea);
            addDateTimeFormatPatterns(model);
            return "assessmentareas/update";
        }
        assessmentArea.merge();
        return "redirect:/assessmentareas/" + encodeUrlPathSegment(assessmentArea.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String AssessmentAreaController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("assessmentArea", AssessmentArea.findAssessmentArea(id));
        addDateTimeFormatPatterns(model);
        return "assessmentareas/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String AssessmentAreaController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        AssessmentArea.findAssessmentArea(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/assessmentareas?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("events")
    public Collection<Event> AssessmentAreaController.populateEvents() {
        return Event.findAllEvents();
    }
    
    @ModelAttribute("messages")
    public Collection<Message> AssessmentAreaController.populateMessages() {
        return Message.findAllMessages();
    }
    
    @ModelAttribute("rapidassessments")
    public Collection<RapidAssessment> AssessmentAreaController.populateRapidAssessments() {
        return RapidAssessment.findAllRapidAssessments();
    }
    
    @ModelAttribute("userentitys")
    public Collection<UserEntity> AssessmentAreaController.populateUserEntitys() {
        return UserEntity.findAllUserEntitys();
    }
    
    Converter<AssessmentArea, String> AssessmentAreaController.getAssessmentAreaConverter() {
        return new Converter<AssessmentArea, String>() {
            public String convert(AssessmentArea assessmentArea) {
                return new StringBuilder().append(assessmentArea.getName()).append(" ").append(assessmentArea.getDescription()).append(" ").append(assessmentArea.getCreated()).toString();
            }
        };
    }
    
    Converter<Event, String> AssessmentAreaController.getEventConverter() {
        return new Converter<Event, String>() {
            public String convert(Event event) {
                return new StringBuilder().append(event.getName()).append(" ").append(event.getDescription()).append(" ").append(event.getCountry()).toString();
            }
        };
    }
    
    Converter<Message, String> AssessmentAreaController.getMessageConverter() {
        return new Converter<Message, String>() {
            public String convert(Message message) {
                return new StringBuilder().append(message.getMessage()).append(" ").append(message.getCreated()).toString();
            }
        };
    }
    
    Converter<RapidAssessment, String> AssessmentAreaController.getRapidAssessmentConverter() {
        return new Converter<RapidAssessment, String>() {
            public String convert(RapidAssessment rapidAssessment) {
                return new StringBuilder().append(rapidAssessment.getCreated()).append(" ").append(rapidAssessment.getGen_est_per_aff()).append(" ").append(rapidAssessment.getGen_est_per_aff_male()).toString();
            }
        };
    }
    
    Converter<UserEntity, String> AssessmentAreaController.getUserEntityConverter() {
        return new Converter<UserEntity, String>() {
            public String convert(UserEntity userEntity) {
                return new StringBuilder().append(userEntity.getUsername()).append(" ").append(userEntity.getPassword()).append(" ").append(userEntity.getEmail()).toString();
            }
        };
    }
    
    @PostConstruct
    void AssessmentAreaController.registerConverters() {
        conversionService.addConverter(getAssessmentAreaConverter());
        conversionService.addConverter(getEventConverter());
        conversionService.addConverter(getMessageConverter());
        conversionService.addConverter(getRapidAssessmentConverter());
        conversionService.addConverter(getUserEntityConverter());
    }
    
    void AssessmentAreaController.addDateTimeFormatPatterns(Model model) {
        model.addAttribute("assessmentArea_created_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    private String AssessmentAreaController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
