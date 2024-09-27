package com.cbidici.site.repository;

import com.cbidici.site.entity.Post;
import com.cbidici.site.entity.Status;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByCreatedAtDesc();

  List<Post> findByStatusOrderByPublishedAtDesc(Status status);
  List<Post> findByStatusOrderByPublishedAtDesc(Limit of, Status status);
  List<Post> findByStatusOrderByReadCountDescCreatedAtDesc(Limit of, Status status);
}
