package com.cbidici.site.repository;

import com.cbidici.site.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByCreatedAtDesc();
}
