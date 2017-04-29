package com.hsbc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Like implements Serializable {
    private long likedUserId;
    private long postId;
}
