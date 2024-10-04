package com.cbidici.site.controller.data;

public record PostResponse (
    Long id,
    String title,
    String description,
    String content,
    String status
){

}
