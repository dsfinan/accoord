package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.ConstructionUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "constructionupdates", formBackingObject = ConstructionUpdate.class)
@RequestMapping("/constructionupdates")
@Controller
public class ConstructionUpdateController {
}
