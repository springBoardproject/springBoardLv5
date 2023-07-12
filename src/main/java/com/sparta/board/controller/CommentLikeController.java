package com.sparta.board.controller;

import com.sparta.board.dto.CommentRequestDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.User;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.CommentLikeService;
import com.sparta.board.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment-like/{commentId}")
public class CommentLikeController {
     // 생성시에는 포스트
     // 아닐때는 delete
     private final CommentLikeService commentLikeService;

     @GetMapping()
     public ResponseEntity<StatusCodesResponseDto> commentLike(@PathVariable Long commentId,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
          User user = userDetails.getUser();
//          StatusCodesResponseDto statusCodesResponseDto = commentLikeService.commentLike(commentId, user);
//          HttpStatusCode httpStatusCode;
//          if (statusCodesResponseDto.getStatusCode() == 201) {
//               httpStatusCode = HttpStatus.CREATED;
//          } else if (statusCodesResponseDto.getStatusCode() == 200) {
//               httpStatusCode = HttpStatus.OK;
//          }
//          return new ResponseEntity<>(statusCodesResponseDto, httpStatusCode) ;
          return new ResponseEntity<>(commentLikeService.commentLike(commentId, user), HttpStatus.OK);
     }
}
