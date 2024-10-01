package com.cbidici.site.controller.data;

import java.time.LocalDateTime;

public record PostCardAdminResponse(
    Long id,
    String title,
    String status,
    LocalDateTime createdAt,
    LocalDateTime publishedAt,
    String url) {
}
