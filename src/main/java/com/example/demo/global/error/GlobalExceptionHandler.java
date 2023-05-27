package com.example.demo.global.error;

import com.example.demo.domain.comment.exception.CommentNotFoundException;
import com.example.demo.domain.topic.exception.PasswordNotMatchedException;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<?> topicNotFoundException(TopicNotFoundException e){
        return new ResponseEntity<>(makeErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<?> PasswordNotMatchedException(PasswordNotMatchedException e){
        return new ResponseEntity<>(makeErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFoundException(CommentNotFoundException e) {
        return new ResponseEntity<>(makeErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity<>(makeErrorResponse("입력 값을 확인해주세요"),HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(String msg){
        return ErrorResponse.builder().msg(msg).build();
    }

}
