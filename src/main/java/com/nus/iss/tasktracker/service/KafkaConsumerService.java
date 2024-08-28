package com.nus.iss.tasktracker.service;

public interface KafkaConsumerService {
    void listenToTaskProcessingEvents(String message);
}
