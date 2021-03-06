// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.web;

import com.accoord.domain.Message;
import com.accoord.domain.UserEntity;
import com.accoord.domain.UserProfile;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

privileged aspect UserProfileController_Roo_Controller {
    
    @Autowired
    private GenericConversionService UserProfileController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String UserProfileController.create(@Valid UserProfile userProfile, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userProfile", userProfile);
            return "userprofiles/create";
        }
        userProfile.persist();
        return "redirect:/userprofiles/" + encodeUrlPathSegment(userProfile.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String UserProfileController.createForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        List dependencies = new ArrayList();
        if (UserEntity.countUserEntitys() == 0) {
            dependencies.add(new String[]{"owner", "userentitys"});
        }
        model.addAttribute("dependencies", dependencies);
        return "userprofiles/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String UserProfileController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userprofile", UserProfile.findUserProfile(id));
        model.addAttribute("itemId", id);
        return "userprofiles/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String UserProfileController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("userprofiles", UserProfile.findUserProfileEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) UserProfile.countUserProfiles() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("userprofiles", UserProfile.findAllUserProfiles());
        }
        return "userprofiles/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String UserProfileController.update(@Valid UserProfile userProfile, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userProfile", userProfile);
            return "userprofiles/update";
        }
        userProfile.merge();
        return "redirect:/userprofiles/" + encodeUrlPathSegment(userProfile.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String UserProfileController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userProfile", UserProfile.findUserProfile(id));
        return "userprofiles/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String UserProfileController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        UserProfile.findUserProfile(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/userprofiles?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("messages")
    public Collection<Message> UserProfileController.populateMessages() {
        return Message.findAllMessages();
    }
    
    @ModelAttribute("userentitys")
    public Collection<UserEntity> UserProfileController.populateUserEntitys() {
        return UserEntity.findAllUserEntitys();
    }
    
    Converter<Message, String> UserProfileController.getMessageConverter() {
        return new Converter<Message, String>() {
            public String convert(Message message) {
                return new StringBuilder().append(message.getMessage()).append(" ").append(message.getCreated()).toString();
            }
        };
    }
    
    Converter<UserEntity, String> UserProfileController.getUserEntityConverter() {
        return new Converter<UserEntity, String>() {
            public String convert(UserEntity userEntity) {
                return new StringBuilder().append(userEntity.getUsername()).append(" ").append(userEntity.getPassword()).append(" ").append(userEntity.getEmail()).toString();
            }
        };
    }
    
    Converter<UserProfile, String> UserProfileController.getUserProfileConverter() {
        return new Converter<UserProfile, String>() {
            public String convert(UserProfile userProfile) {
                return new StringBuilder().append(userProfile.getBio()).append(" ").append(userProfile.getSkype()).append(" ").append(userProfile.getTwitter()).toString();
            }
        };
    }
    
    @PostConstruct
    void UserProfileController.registerConverters() {
        conversionService.addConverter(getMessageConverter());
        conversionService.addConverter(getUserEntityConverter());
        conversionService.addConverter(getUserProfileConverter());
    }
    
    private String UserProfileController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
