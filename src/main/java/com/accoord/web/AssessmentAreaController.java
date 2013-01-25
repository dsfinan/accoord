package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.AssessmentArea;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "assessmentareas", formBackingObject = AssessmentArea.class)
@RequestMapping("/assessmentareas")
@Controller
public class AssessmentAreaController {
}
