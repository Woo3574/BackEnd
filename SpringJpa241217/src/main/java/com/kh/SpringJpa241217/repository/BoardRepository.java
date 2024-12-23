package com.kh.SpringJpa241217.repository;

import com.kh.SpringJpa241217.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 제목검색
    List<Board> findByTitleContaining(String keyword);

    // 제목과 내용 검색관련
    List<Board> findByTitleContainingOrContentContaining(String title, String content);

    @EntityGraph(attributePaths = "comments")
    Optional<Board> findById(Long id);

}
