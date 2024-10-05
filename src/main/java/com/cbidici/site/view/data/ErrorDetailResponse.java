package com.cbidici.site.view.data;

import org.springframework.http.HttpStatus;

public record ErrorDetailResponse(
    HttpStatus status,
    String message
) {
}
