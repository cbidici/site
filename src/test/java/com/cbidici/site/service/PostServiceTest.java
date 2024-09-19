package com.cbidici.site.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.cbidici.site.entity.Post;
import com.cbidici.site.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

  @Mock
  private PostRepository repository;

  @InjectMocks
  private PostService service;

  @Test
  void shouldGetAllPosts() {
    service.getAllPosts();
    verify(repository).findAllByOrderByCreatedAtDesc();
  }

  @Test
  void shouldCreatePost() {
    var post = new Post();
    service.createPost(post);
    verify(repository).save(post);
  }

  @Test
  void shouldCreateWithCreateDate() {
    var postCaptor = ArgumentCaptor.forClass(Post.class);
    var post = new Post();
    service.createPost(post);
    verify(repository).save(postCaptor.capture());
    assertThat(postCaptor.getValue().getCreatedAt()).isNotNull();
  }

  @Test
  void shouldUpdatePost() {
    var localDateTime = LocalDateTime.now();
    var postCaptor = ArgumentCaptor.forClass(Post.class);
    var postUpdate = Post.builder()
        .title("Update Title")
        .content("Update Content")
        .createdAt(localDateTime)
        .build();

    when(repository.findById(eq(10L))).thenReturn(Optional.of(Post.builder().createdAt(localDateTime).build()));
    service.updatePost(10L, postUpdate);

    verify(repository).save(postCaptor.capture());
    assertThat(postCaptor.getValue().getTitle()).isEqualTo("Update Title");
    assertThat(postCaptor.getValue().getContent()).isEqualTo("Update Content");
    assertThat(postCaptor.getValue().getCreatedAt()).isEqualTo(localDateTime);
  }

  @Test
  void shouldThrowExceptionIfPostNotFound() {
    when(repository.findById(10L)).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> service.updatePost(10L, new Post()));
    verify(repository).findById(10L);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void shouldDelete() {
    service.deletePost(10L);
    verify(repository).deleteById(10L);
  }
}