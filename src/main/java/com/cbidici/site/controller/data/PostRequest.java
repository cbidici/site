package com.cbidici.site.controller.data;

public record PostRequest (
    String title,
    String description,
    String content
) {

}
