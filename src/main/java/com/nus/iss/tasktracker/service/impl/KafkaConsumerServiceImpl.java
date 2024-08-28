package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.service.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = {"UserInfoCrated","UserInfoDeleted", "GroupInfoCreated", "GroupInfoDeleted"}, groupId = "task-processing-group")
    public void listenToTaskProcessingEvents( ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String message = record.value();
        System.out.println("topic]: " + topic);
        System.out.println("Receive messages: " + message);

        switch (topic) {
            case "UserInfoCreated":
                handleUserInfoCreated(message);
                break;
            case "UserInfoDeleted":
                handleUserInfoDeleted(message);
                break;
            case "GroupInfoCreated":
                handleGroupInfoCreated(message);
                break;
            case "GroupInfoDeleted":
                handleGroupInfoDeleted(message);
                break;
        }
    }

    @Override
    public void handleUserInfoCreated(String message) {
        System.out.println("Handling UserInfoCreated: " + message);
    }
    @Override
    public void handleUserInfoDeleted(String message) {
        System.out.println("Handling UserInfoDeleted: " + message);
    }

    @Override
    public void handleGroupInfoCreated(String message) {
        System.out.println("Handling GroupInfoCreated: " + message);
    }
    @Override
    public void handleGroupInfoDeleted(String message) {
        System.out.println("Handling GroupInfoDeleted: " + message);
    }
}
