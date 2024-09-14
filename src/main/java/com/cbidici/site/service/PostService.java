package com.cbidici.site.service;

import com.cbidici.site.repository.PostRepository;
import com.cbidici.site.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public List<Post> getAllPosts() {
    return postRepository.findAllByOrderByCreatedAtDesc();
  }

  public void savePost(Post post) {
    post.setCreatedAt(LocalDateTime.now());
    postRepository.save(post);
  }

  public Optional<Post> getPostById(Long id) {
    return postRepository.findById(id);
  }

  public Post updatePost(Long id, Post updatedPost) {
    return postRepository.findById(id).map(post -> {
      post.setTitle(updatedPost.getTitle());
      post.setContent(updatedPost.getContent());
      post.setCreatedAt(LocalDateTime.now());
      return postRepository.save(post);
    }).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
  }

  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }
}