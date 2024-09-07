package com.nus.iss.tasktracker.service.impl;

//import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.service.KafkaConsumerService;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.KafkaTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final TaskInfoService taskInfoService;

    @Autowired
    public KafkaConsumerServiceImpl(TaskInfoService taskInfoService){
        this.taskInfoService=taskInfoService;
    }

    @Override
    @KafkaListener(topics =  {KafkaTopics.USER_INFO_CREATED,KafkaTopics.USER_INFO_DELETED, KafkaTopics.GROUP_INFO_CREATED, KafkaTopics.GROUP_INFO_DELETED}, groupId = KafkaTopics.TASK_PROCESSING_GROUP)
    public void listenToTaskProcessingEvents( ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String message = record.value();
        System.out.println("topic]: " + topic);
        System.out.println("Receive messages: " + message);

        switch (topic) {
            case KafkaTopics.USER_INFO_CREATED:
                handleUserInfoCreated(message);
                break;
            case KafkaTopics.USER_INFO_DELETED:
                handleUserInfoDeleted(message);
                break;
            case KafkaTopics.GROUP_INFO_CREATED:
                handleGroupInfoCreated(message);
                break;
            case KafkaTopics.GROUP_INFO_DELETED:
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
        //Todo: ReleaseActiveTask by UserId
        //taskInfoService.releaseActiveTaskAssignedToUser("1");
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
