package com.panneer.service;

import com.panneer.entity.Like;
import com.panneer.entity.User;
import com.panneer.repository.LikeRepository;
import com.panneer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public void likePost(Long userId, String postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean alreadyLiked = likeRepository.findByUserAndPostId(user, postId).isPresent();
        if (alreadyLiked) {
            throw new RuntimeException("Post already liked");
        }

        Like like = Like.builder()
                .user(user)
                .postId(postId)
                .build();

        likeRepository.save(like);
    }

    public void unlikePost(Long userId, String postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        likeRepository.deleteByUserAndPostId(user, postId);
    }

    public List<Like> getLikesForPost(String postId) {
        return likeRepository.findByPostId(postId);
    }
}