package com.cbidici.site.post;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(Long id) {
    super(String.format("Post not found. Id : " + id));
  }
}
