package com.example.demo.domain.topic.api;

import com.example.demo.domain.topic.application.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
public class OptionApi {
    private final OptionService optionService;
    public OptionApi(OptionService optionService) {
        this.optionService = optionService;
    }
    @PostMapping("/topics/{topicId}/vote")
    public ResponseEntity<?> chooseOption(@PathVariable long topicId,
                                         @RequestBody Map<String,String> vote){
        optionService.chooseOption(topicId, vote.get("vote"));
        return ResponseEntity.ok(convertToMap("votedTopicId",topicId));
    }
    private Map<String,Long> convertToMap(String name, long topicId){
        return Map.of(name,topicId);
    }
}
