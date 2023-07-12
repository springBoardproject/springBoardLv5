package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.User;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.BoardLikeService;
import com.sparta.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardLikeController {
//    private final BoardLikeService boardLikeService;

//    @PostMapping("/{boardId}/like")
//    public ResponseEntity<StatusCodesResponseDto> likeBoard(@PathVariable Long boardId) {
//        Board board =
//        return new ResponseEntity<>(boardLikeService.likeBoard(boardId), HttpStatus.CREATED) ;
//    }


}
