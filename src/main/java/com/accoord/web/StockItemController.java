package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.StockItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "stockitems", formBackingObject = StockItem.class)
@RequestMapping("/stockitems")
@Controller
public class StockItemController {
}
