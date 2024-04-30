package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom database queries can be defined here
}
