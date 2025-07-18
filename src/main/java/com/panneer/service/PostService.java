package com.panneer.service;

import com.panneer.document.Post;
import com.panneer.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(Long userId, String imageUrl, String caption, List<String> hashtags) {
        Post post = Post.builder()
                .userId(userId)
                .imageUrl(imageUrl)
                .caption(caption)
                .hashtags(hashtags)
                .createdAt(Instant.now())
                .build();
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }
}