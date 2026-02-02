package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Fetch all posts ordered by newest first
    List<Post> findAllByOrderByCreatedAtDesc();

    // Find posts by a specific author
    List<Post> findByAuthorUsername(String username);

    long countByAuthorUsername(String username);
    // Generates: SELECT p FROM Post p WHERE p.author.username = :username
    List<Post> findByAuthorUsernameOrderByCreatedAtDesc(String username);
}
