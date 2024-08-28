package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.KafkaTopic;
import com.nus.iss.tasktracker.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC_TASK_INFO_CREATED="TaskInfoCreated";
    private static final String TOPIC_TASK_INFO_DELETED="TaskInfoDeleted";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public void sendMessage(KafkaTopic topic, String message) {
        try {
            kafkaTemplate.send(topic.getTopicName(),message);
            System.out.println("Message sent successfully to topic " + topic);
        } catch (Exception e ){
            System.out.println("Failed to send message: " + e.getMessage());
            log.error(e.getMessage());
            throw new RuntimeException("Failed to send message to Kafka");
        }

    }
}
