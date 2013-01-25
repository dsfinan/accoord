package com.accoord.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.accoord.domain.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "comments", formBackingObject = Comment.class)
@RequestMapping("/comments")
@Controller
public class CommentController {
}
