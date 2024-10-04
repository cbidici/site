package com.cbidici.site.controller.data;

public record PostUpdateView (
    Long id,
    String title,
    String description,
    String content,
    String url
) {

}
