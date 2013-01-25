package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Equipment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "equipments", formBackingObject = Equipment.class)
@RequestMapping("/equipments")
@Controller
public class EquipmentController {
}
