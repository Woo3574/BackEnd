package com.kh.SpringJpa241217.service;

import com.kh.SpringJpa241217.dto.BoardReqDto;
import com.kh.SpringJpa241217.entity.Board;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.repository.BoardRepository;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 의존성 주입
    private final MemberRepository memberRepository;
    // 게시글 등록
    @Transactional
    public boolean saveBoard(BoardReqDto boardReqDto) {
        Member member = memberRepository.findByEmail(boardReqDto.getEmail())
                .orElseThrow(()->new RuntimeException("해당 회원이 존재하지 않습니다."));

        Board board = new Board();
        board.setTitle(boardReqDto.getTitle());
        board.setContent(boardReqDto.getContent());
        board.setImgPAth(board.getImgPAth());
        board.setMember(member);
    }

}
