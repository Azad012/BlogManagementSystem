package com.azad.BlogManagementSystem.service;

import com.azad.BlogManagementSystem.entity.Post;
import com.azad.BlogManagementSystem.entity.PostLike;
import com.azad.BlogManagementSystem.entity.User;
import com.azad.BlogManagementSystem.repository.LikeRepository;
import com.azad.BlogManagementSystem.repository.PostRepository;
import com.azad.BlogManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public String toggleLike(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        return likeRepository.findByPostIdAndUserId(postId, user.getId())
                .map(like -> {
                    likeRepository.delete(like);
                    return "Post Unliked";
                })
                .orElseGet(() -> {
                    PostLike newLike = new PostLike();
                    newLike.setPost(post);
                    newLike.setUser(user);
                    likeRepository.save(newLike);
                    return "Post Liked";
                });
    }

}
