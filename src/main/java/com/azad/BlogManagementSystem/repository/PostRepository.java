package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
