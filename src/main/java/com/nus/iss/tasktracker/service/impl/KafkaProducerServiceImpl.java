package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic,message);
            System.out.println("Message sent successfully to topic " + topic);
        } catch (Exception e ){
            System.out.println("Failed to send message: " + e.getMessage());
            log.error(e.getMessage());
        }

    }
}
