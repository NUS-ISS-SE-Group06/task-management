package com.nus.iss.tasktracker.service;


public interface KafkaProducerService {
    void sendMessage(String topic, String message);
}
