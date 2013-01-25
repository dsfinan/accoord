package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.UserProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "userprofiles", formBackingObject = UserProfile.class)
@RequestMapping("/userprofiles")
@Controller
public class UserProfileController {
}
