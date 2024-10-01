package com.cbidici.site.post;

import com.cbidici.site.post.repository.PostRepository;
import com.cbidici.site.post.repository.PostSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository repository;

  public void create(Long userId, String title, String content) {
    repository.save(new Post(userId, title, content));
  }

  public void update(Long id, String title, String content) {
    var post = repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    post.update(title, content);
    repository.save(post);
  }

  public Post get(Long id) {
    return repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
  }

  public void publish(Long id) {
    var post = get(id);
    post.publish();
    repository.save(post);
  }

  public void withdraw(Long id) {
    var post = get(id);
    post.withdraw();
    repository.save(post);
  }

  public void increaseReadCount(Long id) {
    var post = get(id);
    post.incReadCount();
    repository.save(post);
  }

  public void delete(Long id) {
    var post = get(id);
    post.delete();
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
