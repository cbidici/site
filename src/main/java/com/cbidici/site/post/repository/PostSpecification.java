package com.cbidici.site.post.repository;

import com.cbidici.site.post.Post;
import com.cbidici.site.post.PostSearch;
import com.cbidici.site.post.Status;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class PostSpecification {

  private final static Map<String, Set<Status>> ROLE_STATUS_PERMISSION = Map.of(
      "ROLE_ANONYMOUS", Set.of(Status.PUBLISHED),
      "ROLE_ADMIN", Arrays.stream(Status.values()).filter(s -> s != Status.DELETED).collect(Collectors.toSet())
  );

  private final static Supplier<Set<Status>> PERMITTED_STATUSES = () -> new HashSet<>(
      SecurityContextHolder.getContext()
          .getAuthentication()
          .getAuthorities()
          .stream()
          .map(GrantedAuthority::getAuthority)
          .map(ROLE_STATUS_PERMISSION::get)
          .flatMap(Collection::stream).toList()
  );


  public static Specification<Post> create(PostSearch search) {
    Specification<Post> where = Specification.where(null);
    return where
        .and(search.getId() != null ? (root, cq, cb) -> cb.equal(root.get("id"), search.getId()) : null)
        .and(hasStatuses(search.getStatuses()));
  }

  private static Specification<Post> hasStatuses(Set<Status> statuses) {
    Set<Status> searchStatuses = new HashSet<>(Optional.ofNullable(statuses).orElse(Set.of()));
    searchStatuses.retainAll(PERMITTED_STATUSES.get());
    return (root, cq, cb) -> cb.in(root.get("status")).value(searchStatuses);
  }

}
