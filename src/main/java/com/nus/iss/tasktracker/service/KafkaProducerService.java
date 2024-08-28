package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.KafkaTopic;

public interface KafkaProducerService {
    void sendMessage(KafkaTopic topic, String message);
}
