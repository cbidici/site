package com.cbidici.site.view;

import com.cbidici.site.view.data.PostTitleResponse;
import com.cbidici.site.post.Post;
import com.cbidici.site.post.PostSearch;
import com.cbidici.site.post.PostService;
import com.cbidici.site.post.PostSort;
import com.cbidici.site.post.Status;
import com.cbidici.site.quote.QuoteService;
import com.cbidici.site.shared.SlugService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final QuoteService quoteService;
  private final PostService postService;
  private final SlugService slugService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("quote", quoteService.getRandom());
    model.addAttribute("mostRecent", getRecentPosts().stream()
        .map(this::map).toList());
    model.addAttribute("mostRead", getMostReadPosts().stream()
        .map(this::map).toList());
    return "home";
  }

  private List<Post> getRecentPosts() {
    return postService
        .search(PostSearch.builder().offset(0).size(5).statuses(Set.of(Status.PUBLISHED)).sort(PostSort.PUBLISHED_DESC).build());
  }

  private List<Post> getMostReadPosts() {
    return postService
        .search(PostSearch.builder().offset(0).size(5).statuses(Set.of(Status.PUBLISHED)).sort(PostSort.READ_COUNT_DESC).build());
  }

  private PostTitleResponse map(Post post) {
    return new PostTitleResponse(
        post.getTitle(),
        "/posts/"+slugService.getSlug(post.getTitle())+"/"+post.getId()
    );
  }
}
