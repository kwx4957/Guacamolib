package com.example.demo.domain.topic.application;

import com.example.demo.domain.topic.dao.TopicRepository;
import com.example.demo.domain.topic.domain.Topic;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OptionService {
    private final TopicRepository topicRepository;

    public OptionService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    @Transactional
    public void chooseOption(long topicId, String vote){
       Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("존재하지 않는 주제에요"));

       if(vote.equals("firstOption")) {
           topic.getSelectedOption().increaseFirstOption();
       } else if (vote.equals("secondOption")) {
           topic.getSelectedOption().increaseSecondOption();
       }
        log.info(topic.getSelectedOption().toString());
    }

}
