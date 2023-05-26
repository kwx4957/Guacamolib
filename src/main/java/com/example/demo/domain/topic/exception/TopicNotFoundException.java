package com.example.demo.domain.topic.exception;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException() {
        super();
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
