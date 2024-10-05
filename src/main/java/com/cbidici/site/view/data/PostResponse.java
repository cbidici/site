package com.cbidici.site.view.data;

public record PostResponse (
    Long id,
    String title,
    String description,
    String content,
    String status
){

}
