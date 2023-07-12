package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.StatusCodesResponseDto;
import com.sparta.board.entity.BoardLike;
import com.sparta.board.entity.User;
import com.sparta.board.repository.BoardLikeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BoardLikeService {
//    private BoardLikeRepository boardLikeRepository;
//    public StatusCodesResponseDto likeBoard(Long boardId) {
//        BoardLike boardLike = boardLikeRepository.findByBoard(boardId);
////        boardLikeRepository.save();
//        return new StatusCodesResponseDto(HttpStatus.OK.value(), "삭제가 완료 되었습니다.");
//    }
}
