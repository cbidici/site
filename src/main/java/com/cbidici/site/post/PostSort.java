package com.cbidici.site.post;

import org.springframework.data.domain.Sort.Direction;

public enum PostSort {
  PUBLISHED_DESC("publishedAt", Direction.DESC),
  READ_COUNT_DESC("readCount", Direction.DESC),
  CREATED_DESC("createdAt", Direction.DESC);

  private final String field;
  private final Direction direction;

  PostSort(String field, Direction direction) {
    this.field = field;
    this.direction = direction;
  }

  public String getField() {
    return this.field;
  }

  public Direction getDirection() {
    return this.direction;
  }
}
