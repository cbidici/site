package com.cbidici.site.post.repository;

import com.cbidici.site.post.Post;
import com.cbidici.site.post.PostSearch;
import com.cbidici.site.post.Status;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

  public static Specification<Post> create(PostSearch search) {
    Specification<Post> where = Specification.where(null);
    return where
        .and(hasStatuses(search.getStatuses()));
  }

  private static Specification<Post> hasStatuses(Set<Status> statuses) {
    if(statuses == null) statuses = new HashSet<>();
    final var statusesToFilter = statuses.stream().filter(s -> s != Status.DELETED).collect(Collectors.toSet());
    if(statuses.isEmpty()) {
      return (root, cq, cb) -> cb.and(cb.notEqual(root.get("status"), Status.DELETED));
    } else {
      return (root, cq, cb) -> cb.in(root.get("status")).value(statusesToFilter);
    }
  }

}
