package com.cbidici.site.controller.data;

public record PostUpdateView (
    Long id,
    String title,
    String content,
    String url
) {

}
