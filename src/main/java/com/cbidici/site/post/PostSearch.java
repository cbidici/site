package com.cbidici.site.post;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostSearch {
  private Long id;
  @Builder.Default
  private Set<Status> statuses = Set.of(Status.values());
  @Builder.Default
  private PostSort sort = PostSort.PUBLISHED_DESC;
  @Builder.Default
  private int offset = 0;
  @Builder.Default
  private int size = 100;
}
