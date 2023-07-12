package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.BoardLike;
import com.sparta.board.entity.User;
import com.sparta.board.repository.BoardLikeRepository;
import com.sparta.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = new Board(requestDto, user);
        // DB 저장 넘겨주기
        Board saveBoard = boardRepository.save(board);
        // Entity -> ResponseDto
        return new BoardResponseDto(saveBoard);
    }

    public List<BoardResponseDto> getAllBoards() {
        // db 조회 넘겨주기
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    public BoardResponseDto getSelectedBoard(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
        // Entity -> ResponseDto
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
        // 권한 확인
        checkAuthority(board, user);
        // 수정
        board.update(requestDto);
        // Entity -> ResponseDto
        return new BoardResponseDto(board);
    }

    // 수정, 삭제시 권한을 확인 (현재 오버로딩으로 처리중이나 내부 로직이 거의 동일하여 합치는게 좋은가 고민중)
    public StatusCodesResponseDto deleteBoard(Long id, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
        // 권한 확인
        checkAuthority(board, user);
        // 삭제
        boardRepository.delete(board);

        return new StatusCodesResponseDto(HttpStatus.OK.value(), "삭제가 완료 되었습니다.");

    }

    @Transactional
    public StatusCodesResponseDto likeBoard(Long id, User user) {
        Board board = findBoard(id);
        BoardLike boardLike = boardLikeRepository.findByBoardAndUser(board, user).orElse(null);
        if(boardLike==null){
            BoardLike newBoardLike = new BoardLike(user,board);
            board.addLike(newBoardLike);
//            newBoardLike.setBoard(board); //외래 키 설정
            return new StatusCodesResponseDto(HttpStatus.OK.value(), "좋아요 성공!");
        }else{
//            boardLikeRepository.delete(boardLike);
                board.removeLike(boardLike);
//            boardLike.setBoard(board); //외래 키 설정
            return new StatusCodesResponseDto(HttpStatus.OK.value(), "좋아요 취소!");
        }
    }

    protected Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );
    }

    public void checkAuthority(Board board, User user) {
        // admin 확인
        if (!user.getRole().getAuthority().equals("ROLE_ADMIN")) {
            // username만 확인하는 것 보다 이쪽이 더 안전하다고 생각하여 작성하였으나 true가 나오지 않음.
//            if (!board.getUser().equals(user)) {
            if (board.getUser().getId() != user.getId()) {
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
            }
        }
    }
}
