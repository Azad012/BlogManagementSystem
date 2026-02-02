package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Get all comments for a specific post
    List<Comment> findByPostId(Long postId);

    // Count comments for a post
    Long countByPostId(Long postId);
    // Fetches all comments for a specific post, newest first
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);
}
