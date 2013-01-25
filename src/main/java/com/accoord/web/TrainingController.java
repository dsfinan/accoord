package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Training;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "trainings", formBackingObject = Training.class)
@RequestMapping("/trainings")
@Controller
public class TrainingController {
}
