package com.cbidici.site.controller.data;

public record PostRequest (
    Long userId,
    String title,
    String content
) {

}
