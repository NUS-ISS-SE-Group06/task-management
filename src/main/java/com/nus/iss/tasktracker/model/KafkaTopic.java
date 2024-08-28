package com.nus.iss.tasktracker.model;

public enum KafkaTopic {
    TASK_INFO_CREATED("TaskInfoCreated"),
    TASK_INFO_DELETED("TaskInfoDeleted");

    private final String topicName;

    KafkaTopic(String topicname) {
        this.topicName=topicname;
    }

    public String getTopicName() {
        return topicName;
    }
}
