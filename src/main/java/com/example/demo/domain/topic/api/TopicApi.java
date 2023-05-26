package com.example.demo.domain.topic.api;

import com.example.demo.domain.topic.application.TopicService;
import com.example.demo.domain.topic.dto.TopicRequest;
import com.example.demo.domain.topic.dto.TopicResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.demo.global.common.HttpStatusResponseEntity.*;

@Slf4j
@RestController
public class TopicApi {

    private final TopicService topicService;
    public TopicApi(TopicService topicService) {
        this.topicService = topicService;
    }
    // 투표수랑 댓글수 리턴 값 리스트 포함
    // hot(댓글+투표수)
    // hot 5개 고정
    // 최신
    // 최신 10개 스크롤
    @GetMapping("/topics")
    public ResponseEntity<?> getListTopic(Pageable pageable){
        log.info("토픽 페이징 요청 " + pageable);
        return ResponseEntity.ok(topicService.getListTopicByNew(pageable));
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<?> getTopic(@PathVariable long topicId){
        return ResponseEntity.ok(TopicResponse.of(topicService.findTopicById(topicId)));
    }

    @PostMapping("/topics")
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequest topicRequest){
        topicService.createTopic(topicRequest);
        return RESPONSE_CREATED;
    }
    @DeleteMapping("/topics/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable long topicId,
                                         @RequestBody Map<String,String> passwordMap){
        topicService.deleteById(topicId,passwordMap.get("password"));
        return RESPONSE_NO_CONTENT;
    }
}
