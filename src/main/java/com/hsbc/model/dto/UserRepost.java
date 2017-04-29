package com.hsbc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserRepost implements Serializable {
    private long userId;
    private long postId;
}
