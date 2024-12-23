package com.kh.SpringJpa241217.repository;

import com.kh.SpringJpa241217.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
