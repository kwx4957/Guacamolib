package com.example.demo.domain.topic.api;

import com.example.demo.domain.topic.application.TopicService;
import com.example.demo.domain.topic.dto.TopicRequest;
import com.example.demo.domain.topic.dto.TopicResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class TopicApi {

    private final TopicService topicService;
    public TopicApi(TopicService topicService) {
        this.topicService = topicService;
    }
    @GetMapping("/topics")
    public ResponseEntity<?> getListTopic(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sort){

        List<?> getListTopics = null;

        if(sort.equals("new")){
            sort = "createdAt";
            Pageable paging = PageRequest.of(page,size,Sort.by(sort).descending());
            getListTopics = topicService.getListTopicByNew(paging);
        } else if (sort.equals("hot")) {
            sort = "createdAt";
            Pageable paging = PageRequest.of(page,size,Sort.by(sort).descending());
            getListTopics = topicService.getListTopicByHot(paging);
        }

        return ResponseEntity.ok(getListTopics);
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<?> getTopic(@PathVariable long topicId){
        return ResponseEntity.ok(TopicResponse.of(topicService.findTopicById(topicId)));
    }

    @PostMapping("/topics")
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequest topicRequest){
        long topicId = topicService.createTopic(topicRequest);
        return ResponseEntity.ok(convertToMap("createdTopicId",topicId));
    }
    @DeleteMapping("/topics/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable long topicId,
                                         @RequestBody Map<String,String> passwordMap){
        topicService.deleteById(topicId,passwordMap.get("password"));
        return ResponseEntity.ok(convertToMap("deletedTopicId",topicId));
    }
    private Map<String,Long> convertToMap(String name, long topicId){
        return Map.of(name,topicId);
    }
}
