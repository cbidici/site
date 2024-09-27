package com.cbidici.site.controller.data;

import com.cbidici.site.entity.Status;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostCardResponse {
  private Long id;
  private String title;
  private Status status;
  private LocalDateTime createdAt;
  private LocalDateTime publishedAt;
  private String url;
}
