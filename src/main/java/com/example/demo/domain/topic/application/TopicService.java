package com.example.demo.domain.topic.application;

import com.example.demo.domain.topic.dao.TopicRepository;
import com.example.demo.domain.topic.domain.Topic;
import com.example.demo.domain.topic.dto.TopicPageResponse;
import com.example.demo.domain.topic.dto.TopicRequest;
import com.example.demo.domain.topic.dto.TopicResponse;
import com.example.demo.domain.topic.exception.PasswordNotMatchedException;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    public Topic findTopicById(long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(()->new TopicNotFoundException("존재하지 않는 주제에요."));
        topic.addViews();
        return topic;
    }

    @Transactional
    public long createTopic(TopicRequest topicRequest){
        return topicRepository.save(topicRequest.toEntity()).getId();
    }

    @Transactional
    public void deleteById(long topicId, String password) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("존재하지 않는 주제에요."));

        if(!topic.getPassword().equals(password)){
            throw new PasswordNotMatchedException("비밀번호를 확인해주세요");
        }

        topicRepository.delete(topic);
    }

    @Transactional(readOnly = true)
    public List<?> getListTopicByNew(Pageable pageable) {
        return getTopicPageResponse(topicRepository.findAll(pageable));
    }

    @Transactional(readOnly = true)
    public List<?> getListTopicByHot(Pageable pageable){
        Page<Topic> topicPage = topicRepository.findAllByCreatedAtAfter(pageable, LocalDateTime.now().minusDays(7));

        topicPage.stream().forEach(Topic::sumCommentCountAndSelectedOptions);
//        List<TopicPageResponse> topicPageResponseList = getTopicPageResponse(topicPage);
//
//        topicPageResponseList.stream().map(T);
//        return
    }

//    private List<TopicPageResponse> getTopicPageResponse(Page<Topic> topicPage) {
//        return topicPage.getContent().stream().map(TopicPageResponse::of).collect(Collectors.toList());
//    }
}
