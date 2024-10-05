package com.cbidici.site.post;

import com.cbidici.site.post.repository.PostRepository;
import com.cbidici.site.post.repository.PostSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository repository;

  @PreAuthorize("hasRole('ADMIN')")
  public void create(Long userId, String title, String description, String content) {
    repository.save(new Post(userId, title, description, content));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void update(Long id, String title, String description, String content) {
    var post = repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    post.update(title, description, content);
    repository.save(post);
  }

  public Post get(Long id) {
    return search(PostSearch.builder().id(id).build()).stream()
        .findFirst()
        .orElseThrow(() -> new PostNotFoundException(id));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void publish(Long id) {
    var post = get(id);
    post.publish();
    repository.save(post);
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void withdraw(Long id) {
    var post = get(id);
    post.withdraw();
    repository.save(post);
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void delete(Long id) {
    var post = get(id);
    post.delete();
    repository.save(post);
  }

  public void increaseReadCount(Long id) {
    var post = get(id);
    post.incReadCount();
    repository.save(post);
  }

  public List<Post> search(PostSearch search) {
    return repository.findAll(
        PostSpecification.create(search),
        getPageRequest(search)
    ).getContent();
  }

  private PageRequest getPageRequest(PostSearch search) {
    return PageRequest.of(
        search.getOffset()/search.getSize(), search.getSize(),
        Sort.by(search.getSort().getDirection(), search.getSort().getField())
    );
  }
}
