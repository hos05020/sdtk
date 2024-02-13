package com.ktds.sport.debate.dto;

import lombok.Data;

@Data
public class PostDeleteRequest {

    private String postWriter;
    private String postPassword;

    public PostDeleteRequest() {
    }

    public PostDeleteRequest(String postWriter, String postPassword) {
        this.postWriter = postWriter;
        this.postPassword = postPassword;
    }


}
