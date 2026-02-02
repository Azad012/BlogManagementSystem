package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.Post;
import com.azad.BlogManagementSystem.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<PostLike, Long> {
    // Check if a specific user liked a specific post
    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    // Count total likes for a post
    long countByPostId(Long postId);

    // Check if like exists (useful for UI state)
    boolean existsByPostIdAndUserId(Long postId, Long userId);
}
