package com.cbidici.site.controller.data;

import java.util.Set;
import org.springframework.security.web.csrf.CsrfToken;

public record Page (
    String requestURI,
    boolean authenticated,
    Set<String> authorities,
    CsrfToken crsf
){

}
