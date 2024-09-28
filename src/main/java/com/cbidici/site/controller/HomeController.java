package com.cbidici.site.controller;

import com.cbidici.site.controller.data.PostCardResponse;
import com.cbidici.site.service.PostService;
import com.cbidici.site.service.QuoteService;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final QuoteService quoteService;
  private final PostService postService;
  private final Slugify slugify;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("quote", quoteService.getQuote());
    model.addAttribute("mostRecent", postService.getRecent().stream()
        .map(post -> PostCardResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .publishedAt(post.getPublishedAt())
            .url("/posts/"+slugify.slugify(post.getTitle())+"/"+post.getId())
            .build()).toList());
    model.addAttribute("mostRead", postService.getMostRead().stream()
        .map(post -> PostCardResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .publishedAt(post.getPublishedAt())
            .url("/posts/"+slugify.slugify(post.getTitle())+"/"+post.getId())
            .build()).toList());
    return "home";
  }

}
