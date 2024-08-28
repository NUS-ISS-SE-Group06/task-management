package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.service.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = {"UserInfoCrated","UserInfoDeleted", "GroupInfoCreated", "GroupInfoDeleted"}, groupId = "task-processing-group")
    public void listenToTaskProcessingEvents(String message) {
        System.out.println("Receive messages: " + message);

    }
}
