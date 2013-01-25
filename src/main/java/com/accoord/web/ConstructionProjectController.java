package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.ConstructionProject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "constructionprojects", formBackingObject = ConstructionProject.class)
@RequestMapping("/constructionprojects")
@Controller
public class ConstructionProjectController {
}
