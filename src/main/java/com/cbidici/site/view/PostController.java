package com.cbidici.site.view;

import com.cbidici.site.view.data.PostCardResponse;
import com.cbidici.site.view.data.PostRequest;
import com.cbidici.site.view.data.PostResponse;
import com.cbidici.site.view.data.PostUpdateView;
import com.cbidici.site.post.Post;
import com.cbidici.site.post.PostSearch;
import com.cbidici.site.post.PostSort;
import com.cbidici.site.post.PostService;
import com.cbidici.site.shared.MarkdownProcessor;
import com.cbidici.site.shared.SlugService;
import com.cbidici.site.user.SpringUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final MarkdownProcessor markdownProcessor;
  private final SlugService slugService;

  @GetMapping("/posts")
  public String posts(HttpServletRequest request, Model model) {
    model.addAttribute("posts", postService.search(PostSearch.builder()
            .sort(request.isUserInRole("ROLE_ADMIN") ? PostSort.CREATED_DESC : PostSort.PUBLISHED_DESC)
            .build())
        .stream()
        .map(post ->new PostCardResponse(
            post.getId(),
            post.getTitle(),
            post.getDescription(),
            post.getStatus().name(),
            request.isUserInRole("ROLE_ADMIN") ? post.getCreatedAt() : null,
            post.getPublishedAt(),
            "/posts/"+slugService.getSlug(post.getTitle())+"/"+post.getId()
        )).toList());
    return "posts";
  }

  @GetMapping( "/posts/*/{id}")
  public String viewPost(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
    var post = postService.get(id);
    if (!request.isUserInRole("ROLE_ADMIN")) {
      postService.increaseReadCount(id);
    }
    model.addAttribute("post", new PostResponse(post.getId(), post.getTitle(), post.getDescription(), markdownProcessor.getHtml(post.getContent()), post.getStatus().name()));
    return "post";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/posts/create")
  public String showNewPostForm(Model model) {
    return "post-create";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/posts/create")
  public String createPost(UsernamePasswordAuthenticationToken token, @ModelAttribute PostRequest postRequest) {
    var springUser = (SpringUser) token.getPrincipal();
    postService.create(springUser.getUser().getId(), postRequest.title(), postRequest.description(), postRequest.content());
    return "redirect:/posts";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/posts/{id}/update")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    Post post = postService.get(id);
    model.addAttribute("post", new PostUpdateView(
        post.getId(),
        post.getTitle(),
        post.getDescription(),
        post.getContent(),
        "/posts/" + slugService.getSlug(post.getTitle())  + "/" + post.getId()));
    return "post-update";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/posts/{id}/update")
  public String updatePost(@PathVariable("id") Long id, @ModelAttribute PostRequest postRequest) {
    postService.update(id, postRequest.title(), postRequest.description(), postRequest.content());
    return "redirect:/posts/" + slugService.getSlug(postRequest.title()) + "/"+id;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/posts/{id}/publish")
  public String publishPost(@PathVariable("id") Long id) {
    postService.publish(id);
    var post = postService.get(id);
    return "redirect:/posts/"+ slugService.getSlug(post.getTitle()) +"/"+id;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/posts/{id}/withdraw")
  public String withDrawPost(@PathVariable("id") Long id) {
    postService.withdraw(id);
    var post = postService.get(id);
    return "redirect:/posts/"+ slugService.getSlug(post.getTitle()) +"/"+id;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/posts/{id}/delete")
  public String deletePost(@PathVariable("id") Long id) {
    postService.delete(id);
    return "redirect:/posts";
  }


}
