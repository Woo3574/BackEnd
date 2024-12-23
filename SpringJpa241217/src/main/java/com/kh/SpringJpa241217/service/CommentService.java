package com.kh.SpringJpa241217.service;


import com.kh.SpringJpa241217.dto.CommentReqDto;
import com.kh.SpringJpa241217.repository.BoardRepository;
import com.kh.SpringJpa241217.repository.CommentRepository;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j // log 이력
@Service // 스프링 컨테이너에 빈 등록
@RequiredArgsConstructor // 생성자를 자동으로 생성
public class CommentService {
    // 의존성 주입을 받아야함
    private final MemberRepository memberRepository; // member 정보를 가져오기 위해서
    private final BoardRepository boardRepository; // 게시글 정보를 가져오기 위해서
    private final CommentRepository commentRepository; // 댓글 작성을 위해서

    // 댓글 등록
    @Transactional
    public boolean commentRegister(CommentReqDto commentReqDto) {
        try {

        } catch (Exception e) {
            log.error("댓글 등록 실패: {}", e.getMessage());
            return false;
        }
    }
}
