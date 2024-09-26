package com.cbidici.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlainController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }
}
