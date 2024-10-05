package com.cbidici.site.view.data;

import java.time.LocalDateTime;

public record PostCardResponse(
    Long id,
    String title,
    String status,
    LocalDateTime createdAt,
    LocalDateTime publishedAt,
    String url) {
}
