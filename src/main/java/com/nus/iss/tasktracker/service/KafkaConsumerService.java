package com.nus.iss.tasktracker.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerService {
    void listenToTaskProcessingEvents(ConsumerRecord<String, String> record);
    void handleUserInfoCreated(String message);
    void handleUserInfoDeleted(String message);
    void handleGroupInfoCreated(String message);
    void handleGroupInfoDeleted(String message);
}
