package com.cbidici.site.controller;

import com.cbidici.site.entity.Post;
import com.cbidici.site.service.MarkdownService;
import com.cbidici.site.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {

  private final PostService postService;
  private final MarkdownService markdownService;

  @GetMapping("/")
  public String viewHomePage(HttpServletRequest request, Model model) {
    List<Post> posts;
    if (request.isUserInRole("ROLE_ADMIN")) {
      posts = postService.getAllPosts();
    } else {
      posts = postService.getPublished();
    }
    model.addAttribute("posts", posts);
    return "posts";
  }

  @GetMapping("/post/{id}")
  public String viewPost(@PathVariable("id") Long id, Model model) {
    Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));
    String htmlContent = markdownService.convertToHtml(post.getContent());
    model.addAttribute("post", post);
    model.addAttribute("content", htmlContent);  // Rendered HTML content
    return "post";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/post/create")
  public String showNewPostForm(Model model) {
    model.addAttribute("post", new Post());
    return "create-post";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/post/create")
  public String savePost(@ModelAttribute("post") Post post) {
    postService.createPost(post);
    return "redirect:/";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/post/{id}/update")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
    model.addAttribute("post", post);
    return "edit-post";  // edit-post.html template
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/post/{id}")
  public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post post) {
    postService.updatePost(id, post);
    return "redirect:/";  // Redirect back to the home page after update
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/post/delete/{id}")
  public String deletePost(@PathVariable("id") Long id) {
    postService.deletePost(id);
    return "redirect:/";  // Redirect back to the home page after deletion
  }
}
