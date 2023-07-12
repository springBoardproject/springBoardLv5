package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.User;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    //게시글 작성
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody @Valid BoardRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return new ResponseEntity<>(boardService.createBoard(requestDto, user), HttpStatus.CREATED) ;
    }
    // 작성시간 기준으로 내림차순 정렬하여 모든 게시글 출력
    @GetMapping()
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return new ResponseEntity<>(boardService.getAllBoards(), HttpStatus.OK);
    }

    //선택한 게시글 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getSelectedBoard(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.getSelectedBoard(id), HttpStatus.OK);
    }

    //선택한 게시글 내용 수정
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id,
                                                        @RequestBody @Valid BoardRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return new ResponseEntity<>(boardService.updateBoard(id, requestDto, user), HttpStatus.OK);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCodesResponseDto> deleteBoard(@PathVariable Long id,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return new ResponseEntity<>(boardService.deleteBoard(id, user), HttpStatus.OK);
    }

    //좋아요
    @PutMapping("/{id}/like")
    public ResponseEntity<StatusCodesResponseDto> likeBoard(@PathVariable Long id,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return new ResponseEntity<>(boardService.likeBoard(id, user), HttpStatus.OK);
    }
}
