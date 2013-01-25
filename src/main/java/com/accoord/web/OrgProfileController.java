package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.OrgProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "orgprofiles", formBackingObject = OrgProfile.class)
@RequestMapping("/orgprofiles")
@Controller
public class OrgProfileController {
}
