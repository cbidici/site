package com.cbidici.site.controller;

import com.cbidici.site.entity.Post;
import com.cbidici.site.service.MarkdownService;
import com.cbidici.site.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  public String viewHomePage(Model model) {
    List<Post> posts = postService.getAllPosts();
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

  @GetMapping("/post/new")
  public String showNewPostForm(Model model) {
    model.addAttribute("post", new Post());
    return "new_post";  // Render a form to create a new post
  }

  @PostMapping("/post/save")
  public String savePost(@ModelAttribute("post") Post post) {
    postService.createPost(post);
    return "redirect:/";
  }

  @GetMapping("/post/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
    model.addAttribute("post", post);
    return "edit-post";  // edit-post.html template
  }

  @PostMapping("/post/update/{id}")
  public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post post) {
    postService.updatePost(id, post);
    return "redirect:/";  // Redirect back to the home page after update
  }

  @GetMapping("/post/delete/{id}")
  public String deletePost(@PathVariable("id") Long id) {
    postService.deletePost(id);
    return "redirect:/";  // Redirect back to the home page after deletion
  }
}
