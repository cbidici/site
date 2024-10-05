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
import org.springframework.util.Assert;

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
  private String description;
  private String content;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "published_at")
  private LocalDateTime publishedAt;
  @Column(name = "read_count")
  private int readCount;

  protected Post(Long userId, String title, String description, String content) {
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.content = content;
    this.status = Status.CREATED;
    this.createdAt = LocalDateTime.now();
  }

  protected void update(String title, String description, String content) {
    this.title = title;
    this.description = description;
    this.content = content;
  }

  protected void publish() {
    Assert.isTrue(
        this.status == Status.CREATED || this.status == Status.WITHDRAWN,
        "Post is not eligible to be published.");
    this.status = Status.PUBLISHED;
    this.publishedAt = LocalDateTime.now();
  }

  protected void withdraw() {
    Assert.isTrue(
        this.status == Status.PUBLISHED,
        "Post is not eligible to be withdrawn."
    );
    this.status = Status.WITHDRAWN;
  }

  protected void delete() {
    this.status = Status.DELETED;
  }

  protected void incReadCount() {
    this.readCount++;
  }
}

