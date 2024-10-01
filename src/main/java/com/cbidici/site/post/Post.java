package com.cbidici.site.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  private String title;
  private String content;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "published_at")
  private LocalDateTime publishedAt;
  @Column(name = "read_count")
  private int readCount;

  protected Post(Long userId, String title, String content) {
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.status = Status.CREATED;
    this.createdAt = LocalDateTime.now();
  }

  protected void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

  protected void publish() {
    assert(this.status == Status.CREATED || this.status == Status.WITHDRAWN);
    this.status = Status.PUBLISHED;
    this.publishedAt = LocalDateTime.now();
  }

  protected void withdraw() {
    assert(this.status == Status.PUBLISHED);
    this.status = Status.WITHDRAWN;
  }

  protected void delete() {
    this.status = Status.DELETED;
  }

  protected void incReadCount() {
    this.readCount++;
  }
}

