package com.ktds.sport.debate.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class PostSearchRequest {

    private int page = 1;
    private String type;
    private String keyword;


    public int getOffset() {
        return Math.max((page - 1) * 4,0);
    }

}
