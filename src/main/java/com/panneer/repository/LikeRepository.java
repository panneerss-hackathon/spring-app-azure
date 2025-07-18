package com.panneer.repository;

import com.panneer.entity.Like;
import com.panneer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(String postId);
    Optional<Like> findByUserAndPostId(User user, String postId);
    void deleteByUserAndPostId(User user, String postId);
}