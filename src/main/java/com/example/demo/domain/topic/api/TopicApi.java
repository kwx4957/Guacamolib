package com.example.demo.domain.topic.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicApi {
    @GetMapping("/topics")
    public List<Topic> getListTopic(){

        return List<Topic>;
    }
    @GetMapping("/topics/{topicId}")
    public Topic getTopic(){

        return Topic;
    }

    @PostMapping("/topics")
    public void createTopic(){

    }
    @DeleteMapping("/topics/{topicId}")
    public void deleteTopic(){

    }


}
