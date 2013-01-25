package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Warehouse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "warehouses", formBackingObject = Warehouse.class)
@RequestMapping("/warehouses")
@Controller
public class WarehouseController {
}
