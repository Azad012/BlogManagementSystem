package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
