package com.UserBlog.Blog.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CsrfTokenAdvice {

    @ModelAttribute("_csrf")
    public CsrfToken addCsrfToken(CsrfToken token) {
        return token;
    }
}
