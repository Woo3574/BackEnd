package com.kh.SpringJpa241217.repository;

import com.kh.SpringJpa241217.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 제목검색
    List<Board> findByTitleContaining(String keyword);

    // 제목과 내용 검색관련
    List<Board> findByTitleContainingOrContentContaining(String title, String content);

}
