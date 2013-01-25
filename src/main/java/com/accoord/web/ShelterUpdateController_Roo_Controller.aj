// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.web;

import com.accoord.domain.ShelterProject;
import com.accoord.domain.ShelterUpdate;
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

privileged aspect ShelterUpdateController_Roo_Controller {
    
    @Autowired
    private GenericConversionService ShelterUpdateController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String ShelterUpdateController.create(@Valid ShelterUpdate shelterUpdate, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("shelterUpdate", shelterUpdate);
            addDateTimeFormatPatterns(model);
            return "shelterupdates/create";
        }
        shelterUpdate.persist();
        return "redirect:/shelterupdates/" + encodeUrlPathSegment(shelterUpdate.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ShelterUpdateController.createForm(Model model) {
        model.addAttribute("shelterUpdate", new ShelterUpdate());
        addDateTimeFormatPatterns(model);
        List dependencies = new ArrayList();
        if (ShelterProject.countShelterProjects() == 0) {
            dependencies.add(new String[]{"project", "shelterprojects"});
        }
        model.addAttribute("dependencies", dependencies);
        return "shelterupdates/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ShelterUpdateController.show(@PathVariable("id") Long id, Model model) {
        addDateTimeFormatPatterns(model);
        model.addAttribute("shelterupdate", ShelterUpdate.findShelterUpdate(id));
        model.addAttribute("itemId", id);
        return "shelterupdates/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ShelterUpdateController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("shelterupdates", ShelterUpdate.findShelterUpdateEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) ShelterUpdate.countShelterUpdates() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("shelterupdates", ShelterUpdate.findAllShelterUpdates());
        }
        addDateTimeFormatPatterns(model);
        return "shelterupdates/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ShelterUpdateController.update(@Valid ShelterUpdate shelterUpdate, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("shelterUpdate", shelterUpdate);
            addDateTimeFormatPatterns(model);
            return "shelterupdates/update";
        }
        shelterUpdate.merge();
        return "redirect:/shelterupdates/" + encodeUrlPathSegment(shelterUpdate.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ShelterUpdateController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shelterUpdate", ShelterUpdate.findShelterUpdate(id));
        addDateTimeFormatPatterns(model);
        return "shelterupdates/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ShelterUpdateController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        ShelterUpdate.findShelterUpdate(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/shelterupdates?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("shelterprojects")
    public Collection<ShelterProject> ShelterUpdateController.populateShelterProjects() {
        return ShelterProject.findAllShelterProjects();
    }
    
    Converter<ShelterProject, String> ShelterUpdateController.getShelterProjectConverter() {
        return new Converter<ShelterProject, String>() {
            public String convert(ShelterProject shelterProject) {
                return new StringBuilder().append(shelterProject.getCreateddate()).append(" ").append(shelterProject.getEstimatedstartdate()).append(" ").append(shelterProject.getEstimatedenddate()).toString();
            }
        };
    }
    
    Converter<ShelterUpdate, String> ShelterUpdateController.getShelterUpdateConverter() {
        return new Converter<ShelterUpdate, String>() {
            public String convert(ShelterUpdate shelterUpdate) {
                return new StringBuilder().append(shelterUpdate.getUpdated()).append(" ").append(shelterUpdate.getActualunitsbuilt()).toString();
            }
        };
    }
    
    @PostConstruct
    void ShelterUpdateController.registerConverters() {
        conversionService.addConverter(getShelterProjectConverter());
        conversionService.addConverter(getShelterUpdateConverter());
    }
    
    void ShelterUpdateController.addDateTimeFormatPatterns(Model model) {
        model.addAttribute("shelterUpdate_updated_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    private String ShelterUpdateController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
