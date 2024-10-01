package com.cbidici.site.controller.data;

import java.time.LocalDateTime;

public record PostCardResponse(
    Long id,
    String title,
    String status,
    LocalDateTime publishedAt,
    String url) {
}
