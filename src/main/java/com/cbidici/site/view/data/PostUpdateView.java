package com.cbidici.site.view.data;

public record PostUpdateView (
    Long id,
    String title,
    String description,
    String content,
    String url
) {

}
