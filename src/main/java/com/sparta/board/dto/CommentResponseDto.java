package com.sparta.board.dto;

import com.sparta.board.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comments;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Integer countLikes;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comments = comment.getComments();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.countLikes = comment.getCommentLikes().size();
    }
}
