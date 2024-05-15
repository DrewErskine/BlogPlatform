package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Authority;
import com.UserBlog.Blog.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Optional<Authority> findByName(String name) {
        return authorityRepository.findById(name);
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }
}
