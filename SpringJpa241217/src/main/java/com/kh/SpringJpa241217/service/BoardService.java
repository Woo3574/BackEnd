package com.kh.SpringJpa241217.service;

import com.kh.SpringJpa241217.dto.BoardReqDto;
import com.kh.SpringJpa241217.dto.BoardResDto;
import com.kh.SpringJpa241217.entity.Board;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.repository.BoardRepository;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 의존성 주입
    private final MemberRepository memberRepository;
    // 게시글 등록
    @Transactional
    public boolean saveBoard(BoardReqDto boardReqDto) {
        try {
            // FrontEnd에서 전달한 이메일로 회원 정보를 가져 옴
            Member member = memberRepository.findByEmail(boardReqDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

            // dto 프론트엔드에서 가져온데이터
            Board board = new Board();
            board.setTitle(boardReqDto.getTitle());
            board.setImgPAth(boardReqDto.getImgPath());
            board.setContent(boardReqDto.getContent());
            board.setMember(member);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            log.error("게시글 등록 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 게시글 상세 조회
    public BoardResDto findByBoardId(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 게시글이 존재하지 않습니다."));

        return convertEntityToDto(board);
    }

    // 게시글 전체 조회
    public List<BoardResDto> findAllBoard() {
        List<Board> boards = boardRepository.findAll(); // DB에 있는 모든 게시글 가져오기
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for(Board board : boards) {
            BoardResDto boardResDto = new BoardResDto();
            boardResDto.setBoardId(board.getId());
            boardResDto.setTitle(board.getTitle());
            boardResDto.setContent(board.getContent());
            boardResDto.setImgPath((board.getImgPAth()));
            boardResDto.setRegDate(board.getRegDate());
            boardResDtoList.add(boardResDto);
        }
        return boardResDtoList;
    }

    // 게시글 검색 기능
    public List<BoardResDto> searchBoard(String keyword) {

    }

    private BoardResDto convertEntityToDto(Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setBoardId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setImgPath((board.getImgPAth()));
        boardResDto.setRegDate(board.getRegDate());
        return boardResDto;
    }
}
