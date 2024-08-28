package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.KafkaTopic;
import com.nus.iss.tasktracker.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC_TASK_INFO_CREATED="TaskInfoCreated";
    private static final String TOPIC_TASK_INFO_DELETED="TaskInfoDeleted";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(KafkaTopic topic, String message) {
        kafkaTemplate.send(topic.getTopicName(),message);
    }
}
