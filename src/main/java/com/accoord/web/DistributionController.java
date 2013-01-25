package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Distribution;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "distributions", formBackingObject = Distribution.class)
@RequestMapping("/distributions")
@Controller
public class DistributionController {
}
