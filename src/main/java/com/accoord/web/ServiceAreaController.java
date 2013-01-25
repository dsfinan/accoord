package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.ServiceArea;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "serviceareas", formBackingObject = ServiceArea.class)
@RequestMapping("/serviceareas")
@Controller
public class ServiceAreaController {
}
