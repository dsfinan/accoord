package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.RapidAssessment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "rapidassessments", formBackingObject = RapidAssessment.class)
@RequestMapping("/rapidassessments")
@Controller
public class RapidAssessmentController {
}
