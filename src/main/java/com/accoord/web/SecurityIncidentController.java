package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.SecurityIncident;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "securityincidents", formBackingObject = SecurityIncident.class)
@RequestMapping("/securityincidents")
@Controller
public class SecurityIncidentController {
}
