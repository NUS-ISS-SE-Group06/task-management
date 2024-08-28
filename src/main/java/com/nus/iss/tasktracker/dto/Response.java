package com.nus.iss.tasktracker.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
public class Response {
    // Getters and Setters
    @Setter
    @Getter
    private LocalDateTime timestamp;
    @Setter
    @Getter
    private int status;
    @Setter
    @Getter
    private String error;
    @Setter
    @Getter
    private String message;
    private Object body;


    public Response(LocalDateTime timestamp, int status, String error, String message, Object body) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.body   = body;

    }
    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }


}

