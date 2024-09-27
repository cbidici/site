package com.cbidici.site.controller;

import com.cbidici.site.service.PostService;
import com.cbidici.site.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final QuoteService quoteService;
  private final PostService postService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("quote", quoteService.getQuote());
    model.addAttribute("mostRecent", postService.getRecent());
    model.addAttribute("mostRead", postService.getMostRead());
    return "home";
  }

}
