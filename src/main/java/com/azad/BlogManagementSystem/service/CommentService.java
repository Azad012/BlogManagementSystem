package com.azad.BlogManagementSystem.service;

import com.azad.BlogManagementSystem.dto.CommentRequest;
import com.azad.BlogManagementSystem.dto.CommentResponse;
import com.azad.BlogManagementSystem.entity.Comment;
import com.azad.BlogManagementSystem.entity.Post;
import com.azad.BlogManagementSystem.entity.User;
import com.azad.BlogManagementSystem.exception.ResourceNotFoundException;
import com.azad.BlogManagementSystem.exception.UnauthorizedActionException;
import com.azad.BlogManagementSystem.repository.CommentRepository;
import com.azad.BlogManagementSystem.repository.PostRepository;
import com.azad.BlogManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(Long postId, CommentRequest request, String username) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setPost(post);
        comment.setAuthor(user);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        // Only comment author or post author should be able to delete comments
        if (!comment.getAuthor().getUsername().equals(username)) throw new RuntimeException("Unauthorized");
        commentRepository.delete(comment);
    }
    public List<CommentResponse> getAllCommentsByPostId(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found with id: " + postId);
        }

        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 2. Edit an existing comment
    @Transactional
    public CommentResponse editComment(Long commentId, CommentRequest request, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // Ownership Check: Only the author can edit
        if (!comment.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedActionException("You can only edit your own comments");
        }

        comment.setText(request.getText());
        return mapToResponse(commentRepository.save(comment));
    }

    // Helper method to convert Entity to DTO
    private CommentResponse mapToResponse(Comment comment) {
        CommentResponse res = new CommentResponse();
        res.setId(comment.getId());
        res.setText(comment.getText());
        res.setAuthorName(comment.getAuthor().getUsername());
        res.setCreatedAt(comment.getCreatedAt());
        return res;
    }

}
