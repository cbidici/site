package com.cbidici.site.service;

import com.cbidici.site.entity.Status;
import com.cbidici.site.repository.PostRepository;
import com.cbidici.site.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public List<Post> getAllPosts() {
    return postRepository.findAllByOrderByCreatedAtDesc();
  }

  public void createPost(Post post) {
    post.setCreatedAt(LocalDateTime.now());
    if(Status.PUBLISHED != post.getStatus()) {
      post.setPublishedAt(LocalDateTime.now());
    }
    postRepository.save(post);
  }

  public Optional<Post> getPostById(Long id) {
    return postRepository.findById(id);
  }

  public void updatePost(Long id, Post updatedPost) {
    postRepository.findById(id).ifPresentOrElse(
        post -> {
          post.setTitle(updatedPost.getTitle());
          post.setContent(updatedPost.getContent());
          if(Status.PUBLISHED != post.getStatus() && Status.PUBLISHED == updatedPost.getStatus()) {
            post.setPublishedAt(LocalDateTime.now());
          }
          post.setStatus(updatedPost.getStatus());
          postRepository.save(post);
        },
        () -> {throw new IllegalArgumentException("Invalid post ID: " + id);});
  }

  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }

  public List<Post> getPublished() {
    return postRepository.findByStatusOrderByPublishedAtDesc(Status.PUBLISHED);
  }

  public List<Post> getRecent() {
    return postRepository.findByStatusOrderByPublishedAtDesc(Limit.of(5), Status.PUBLISHED);
  }

  public List<Post> getMostRead() {
    return postRepository.findByStatusOrderByReadCountDescCreatedAtDesc(Limit.of(5), Status.PUBLISHED);
  }

  public void incrementRead(Long id) {
    postRepository.findById(id).ifPresent(
        post -> {
          post.setReadCount(post.getReadCount()+1);
          postRepository.save(post);
        }
    );
  }
}