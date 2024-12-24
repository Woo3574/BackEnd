package com.kh.SpringJpa241217.service;

import com.kh.SpringJpa241217.dto.BoardReqDto;
import com.kh.SpringJpa241217.dto.BoardResDto;
import com.kh.SpringJpa241217.dto.CommentReqDto;
import com.kh.SpringJpa241217.dto.CommentResDto;
import com.kh.SpringJpa241217.entity.Board;
import com.kh.SpringJpa241217.entity.Comment;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.repository.BoardRepository;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));

        return convertEntityToDto(board);
    }

    // 게시글 전체 조회
    public List<BoardResDto> findAllBoard() {
        List<Board> boards = boardRepository.findAll(); // DB에 있는 모든 게시글 가져오기
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boards) {
            // convertEntityToDto 를 통해서 BoardResDto 반환 받아서 List 에 추가
            boardResDtoList.add(convertEntityToDtoWithoutComments(board));
        }
        return boardResDtoList;
    }

    // 게시글 검색 기능
    public List<BoardResDto> searchBoard(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boards) {
            // convertEntityToDto 를 통해서 BoardResDto 반환 받아서 List 에 추가
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }

    // size 는 프론트엔드에서 지정해줘야함
    // 게시글 페이지 수 조회, 첫 페이지 화면열때만(렌더링) 호출하면됨
    public int getBoardPageCount(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardRepository.findAll(pageRequest).getTotalPages();
    }

    // 게시글 페이징
    public List<BoardResDto> pagingBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Board> boards = boardRepository.findAll(pageable).getContent();
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boards) {
            // convertEntityToDto를 통해서 BoardResDto반환 받아서 List에 추가
            boardResDtoList.add(convertEntityToDtoWithoutComments(board));
        }
        return boardResDtoList;
    }

    // 게시글 삭제
    public boolean deleteBoard(Long id, String email) {
       try {
           // 삭제전에 존재 여부 확인
           Board board = boardRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
           // 삭제 진행
           if (board.getMember().getEmail().equals(email)) {
               boardRepository.delete(board);
               return true;
           } else {
               log.error("게시글은 작성자만 지울 수 있습니다.");
               return false;
           }
       } catch (Exception e) {
           log.error("게시글 삭제 실패 : {}", e.getMessage());
           return false;
       }
    }

    // 게시글 수정
    public boolean modifyBoard(Long id, BoardReqDto boardReqDto) {
       try {
           Board board = boardRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));

           memberRepository.findByEmail(boardReqDto.getEmail())
                   .orElseThrow(() -> new RuntimeException("회원이 존재 하지 않습니다."));

           if (board.getMember().getEmail().equals(boardReqDto.getEmail())) {
               board.setTitle(boardReqDto.getTitle());
               board.setContent(boardReqDto.getContent());
               board.setImgPAth(boardReqDto.getImgPath());
               boardRepository.save(board); // update
               return true;
           } else {
               log.error("게시글은 작성자만 수정 할 수 있습니다.");
               return false;
           }
       } catch (Exception e) {
           log.error("게시글 수정 실패");
           return false;
       }
    }

    // 게시글 검색 (제목과 내용)
    public List<BoardResDto> searchTitleOrContentBoard(String title, String content) {
        List<Board> boards = boardRepository.findByTitleContainingOrContentContaining(title, content);

        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boards) {
            // convertEntityToDto를 통해서 BoardResDto반환 받아서 List에 추가
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }

    // 댓글 목록 조회
    public List<CommentResDto> commentList(Long boardId) {
        try {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(()-> new RuntimeException("해당 게시글이 없습니다"));

            List<CommentResDto> commentResDtoList = new ArrayList<>();
            for (Comment comment : board.getComments()) {
                CommentResDto commentResDto = new CommentResDto();
                commentResDto.setEmail(comment.getMember().getEmail());
                commentResDto.setBoardId(comment.getBoard().getId());
                commentResDto.setCommentId(comment.getCommentId());
                commentResDto.setContent(comment.getContent());
                commentResDto.setRegDate(comment.getRegDate());
                commentResDtoList.add(commentResDto);
            }
            return commentResDtoList;
        } catch (Exception e) {
            log.error("게시글에 대한 댓글 조회 실패 : {}",e.getMessage());
            return null;
        }
    }

    @Transactional
    public void addComment(Long boardId, CommentReqDto commentReqDto) {
        // id로 board 객체 가져오기
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));

        // email로 member 객체 가져오기
        Member member = memberRepository.findByEmail(commentReqDto.getEmail())
                .orElseThrow(()-> new RuntimeException("회원 정보가 존재하지 않습니다."));
        // Dto -> entity로 변환
        Comment comment = new Comment();
        comment.setContent(commentReqDto.getContent());
        comment.setMember(member);
        comment.setBoard(board);
        board.addComment(comment);
        boardRepository.save(board);
    }

    private BoardResDto convertEntityToDto(Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setBoardId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setImgPath((board.getImgPAth()));
        boardResDto.setRegDate(board.getRegDate());

        List<CommentResDto> commentResDtoList = new ArrayList<>();
        for (Comment comment : board.getComments()) {
            CommentResDto commentResDto = new CommentResDto();
            commentResDto.setEmail(comment.getMember().getEmail());
            commentResDto.setBoardId(comment.getBoard().getId());
            commentResDto.setCommentId(comment.getCommentId());
            commentResDto.setContent(comment.getContent());
            commentResDto.setRegDate(comment.getRegDate());
            commentResDtoList.add(commentResDto);
        }
        boardResDto.setComments(commentResDtoList);
        return boardResDto;
    }

    // 댓글 제외 DTO
    private BoardResDto convertEntityToDtoWithoutComments(Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setBoardId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setImgPath((board.getImgPAth()));
        boardResDto.setRegDate(board.getRegDate());
        boardResDto.setComments(new ArrayList<>());
        return boardResDto;
    }
}
