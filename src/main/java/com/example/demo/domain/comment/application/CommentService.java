package com.example.demo.domain.comment.application;

import com.example.demo.domain.comment.dao.CommentRepository;
import com.example.demo.domain.comment.domain.Comment;
import com.example.demo.domain.comment.dto.CommentPageResponse;
import com.example.demo.domain.comment.dto.CommentRequest;
import com.example.demo.domain.comment.dto.CommentResponse;
import com.example.demo.domain.comment.exception.CommentNotFoundException;
import com.example.demo.domain.topic.dao.TopicRepository;
import com.example.demo.domain.topic.exception.PasswordNotMatchedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;
    public CommentService(CommentRepository commentRepository, TopicRepository topicRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
    }
    public boolean existTopicById(long topicId){
        return topicRepository.existsById(topicId);
    }
    @Transactional
    public void createComment(long topicId, CommentRequest commentRequest) {
        commentRepository.save(commentRequest.toEntity(topicId));
    }

    @Transactional
    public void deleteComment(long commentId, String password) {
       Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("존재하지 않는 댓글이에요."));

       if(!comment.getPassword().equals(password)){
           throw new PasswordNotMatchedException("비밀번호를 확인해주세요");
       }

       commentRepository.delete(comment);
    }

    public CommentPageResponse getListComment(long topicId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAllByTopicId(topicId, pageable);
        return getCommentPageResponse(commentPage, pageable);
    }

    private CommentPageResponse getCommentPageResponse(Page<Comment> comments, Pageable pageable) {
        List<CommentResponse> commentResponses = comments.getContent().stream().map(CommentResponse::of).collect(Collectors.toList());
        return CommentPageResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(commentResponses)
                .build();
    }
}
