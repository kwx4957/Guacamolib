package com.example.demo.domain.topic.application;


import com.example.demo.domain.topic.dao.TopicRepository;
import com.example.demo.domain.topic.domain.Topic;
import com.example.demo.domain.topic.dto.TopicRequest;
import com.example.demo.domain.topic.dto.TopicResponse;
import com.example.demo.domain.topic.exception.PasswordNotMatchedException;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import com.example.demo.global.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional(readOnly = true)
    public Topic findTopicById(long id) {
        return topicRepository.findById(id).orElseThrow(()->new TopicNotFoundException("존재하지 않는 주제에요."));
    }

    @Transactional
    public void createTopic(TopicRequest topicRequest){
        topicRepository.save(topicRequest.toEntity());
    }

    @Transactional
    public void deleteById(long topicId, String password) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("존재하지 않는 주제에요."));

        if(!topic.getPassword().equals(password)){
            throw new PasswordNotMatchedException("비밀번호를 확인해주세요");
        }

        topicRepository.delete(topic);
    }

    public PageResponse getListTopicByNew(Pageable pageable) {
        Page<Topic> topicPage = topicRepository.findAll(pageable);
        return getTopicPageResponse(topicPage);
    }

    private PageResponse getTopicPageResponse(Page<Topic> topicPage) {
        List<TopicResponse> topicResponses = topicPage.getContent().stream().map(TopicResponse::of).collect(Collectors.toList());
        return PageResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(topicResponses)
                .build();
    }
}
