// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.web;

import com.accoord.domain.ServiceArea;
import com.accoord.domain.ShelterProject;
import com.accoord.domain.ShelterUpdate;
import com.accoord.shared.ShelterType;
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

privileged aspect ShelterProjectController_Roo_Controller {
    
    @Autowired
    private GenericConversionService ShelterProjectController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String ShelterProjectController.create(@Valid ShelterProject shelterProject, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("shelterProject", shelterProject);
            addDateTimeFormatPatterns(model);
            return "shelterprojects/create";
        }
        shelterProject.persist();
        return "redirect:/shelterprojects/" + encodeUrlPathSegment(shelterProject.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ShelterProjectController.createForm(Model model) {
        model.addAttribute("shelterProject", new ShelterProject());
        addDateTimeFormatPatterns(model);
        List dependencies = new ArrayList();
        if (ServiceArea.countServiceAreas() == 0) {
            dependencies.add(new String[]{"area", "serviceareas"});
        }
        model.addAttribute("dependencies", dependencies);
        return "shelterprojects/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ShelterProjectController.show(@PathVariable("id") Long id, Model model) {
        addDateTimeFormatPatterns(model);
        model.addAttribute("shelterproject", ShelterProject.findShelterProject(id));
        model.addAttribute("itemId", id);
        return "shelterprojects/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ShelterProjectController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("shelterprojects", ShelterProject.findShelterProjectEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) ShelterProject.countShelterProjects() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("shelterprojects", ShelterProject.findAllShelterProjects());
        }
        addDateTimeFormatPatterns(model);
        return "shelterprojects/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ShelterProjectController.update(@Valid ShelterProject shelterProject, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("shelterProject", shelterProject);
            addDateTimeFormatPatterns(model);
            return "shelterprojects/update";
        }
        shelterProject.merge();
        return "redirect:/shelterprojects/" + encodeUrlPathSegment(shelterProject.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ShelterProjectController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shelterProject", ShelterProject.findShelterProject(id));
        addDateTimeFormatPatterns(model);
        return "shelterprojects/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ShelterProjectController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        ShelterProject.findShelterProject(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/shelterprojects?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("serviceareas")
    public Collection<ServiceArea> ShelterProjectController.populateServiceAreas() {
        return ServiceArea.findAllServiceAreas();
    }
    
    @ModelAttribute("shelterupdates")
    public Collection<ShelterUpdate> ShelterProjectController.populateShelterUpdates() {
        return ShelterUpdate.findAllShelterUpdates();
    }
    
    @ModelAttribute("sheltertypes")
    public Collection<ShelterType> ShelterProjectController.populateShelterTypes() {
        return Arrays.asList(ShelterType.class.getEnumConstants());
    }
    
    Converter<ServiceArea, String> ShelterProjectController.getServiceAreaConverter() {
        return new Converter<ServiceArea, String>() {
            public String convert(ServiceArea serviceArea) {
                return new StringBuilder().append(serviceArea.getName()).append(" ").append(serviceArea.getDescription()).append(" ").append(serviceArea.getCreated()).toString();
            }
        };
    }
    
    Converter<ShelterProject, String> ShelterProjectController.getShelterProjectConverter() {
        return new Converter<ShelterProject, String>() {
            public String convert(ShelterProject shelterProject) {
                return new StringBuilder().append(shelterProject.getCreateddate()).append(" ").append(shelterProject.getEstimatedstartdate()).append(" ").append(shelterProject.getEstimatedenddate()).toString();
            }
        };
    }
    
    Converter<ShelterUpdate, String> ShelterProjectController.getShelterUpdateConverter() {
        return new Converter<ShelterUpdate, String>() {
            public String convert(ShelterUpdate shelterUpdate) {
                return new StringBuilder().append(shelterUpdate.getUpdated()).append(" ").append(shelterUpdate.getActualunitsbuilt()).toString();
            }
        };
    }
    
    @PostConstruct
    void ShelterProjectController.registerConverters() {
        conversionService.addConverter(getServiceAreaConverter());
        conversionService.addConverter(getShelterProjectConverter());
        conversionService.addConverter(getShelterUpdateConverter());
    }
    
    void ShelterProjectController.addDateTimeFormatPatterns(Model model) {
        model.addAttribute("shelterProject_createddate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        model.addAttribute("shelterProject_estimatedenddate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        model.addAttribute("shelterProject_estimatedstartdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    private String ShelterProjectController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
