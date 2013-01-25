package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Office;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "offices", formBackingObject = Office.class)
@RequestMapping("/offices")
@Controller
public class OfficeController {
}
