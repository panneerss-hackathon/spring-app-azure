package com.panneer.repository;

import com.panneer.entity.Comment;
import com.panneer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(String postId);
    List<Comment> findByUser(User user);
}