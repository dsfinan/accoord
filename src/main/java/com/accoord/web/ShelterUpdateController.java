package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.ShelterUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "shelterupdates", formBackingObject = ShelterUpdate.class)
@RequestMapping("/shelterupdates")
@Controller
public class ShelterUpdateController {
}
