package com.sparta.board.service;

import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.CommentLike;
import com.sparta.board.entity.User;
import com.sparta.board.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentService commentService;



    public StatusCodesResponseDto commentLike(Long commentId, User user) {
        Comment comment = commentService.findComment(commentId);
        CommentLike checkCommentLike = commentLikeRepository.findByCommentAndUser(comment, user)
                .orElse(null);
        if (checkCommentLike == null) {
            CommentLike commentLike = new CommentLike(user, comment);
            commentLikeRepository.save(commentLike);
            return new StatusCodesResponseDto(HttpStatus.CREATED.value(), "좋아요 성공.");

        } else {
            commentLikeRepository.delete(checkCommentLike);
            return new StatusCodesResponseDto(HttpStatus.OK.value(), "좋아요 취소.");

        }
    }
}
