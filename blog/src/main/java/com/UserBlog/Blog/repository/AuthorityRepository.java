package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
