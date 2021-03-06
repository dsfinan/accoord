package com.accoord.web;


import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Event;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "events", formBackingObject = Event.class)
@RequestMapping("/events")
@Controller
public class EventController {
}
