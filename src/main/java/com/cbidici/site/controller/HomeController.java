package com.cbidici.site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  @GetMapping("/")
  public String index() {
    return "redirect:/posts";
  }

  @GetMapping("/home")
  public String home(Model model) {
    return "home";
  }

}
