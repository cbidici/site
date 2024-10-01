package com.cbidici.site.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.cbidici.site.post.Post;
import com.cbidici.site.post.PostService;
import com.cbidici.site.post.repository.PostRepository;
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
  void testSave() {
    var postCaptor = ArgumentCaptor.forClass(Post.class);
    service.create(10L, "Title", "Content");
    verify(repository).save(postCaptor.capture());
    assertThat(postCaptor.getValue().getUserId()).isEqualTo(10L);
    assertThat(postCaptor.getValue().getTitle()).isEqualTo("Title");
    assertThat(postCaptor.getValue().getContent()).isEqualTo("Content");
  }


}