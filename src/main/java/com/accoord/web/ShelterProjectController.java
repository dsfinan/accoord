package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.ShelterProject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "shelterprojects", formBackingObject = ShelterProject.class)
@RequestMapping("/shelterprojects")
@Controller
public class ShelterProjectController {
}
