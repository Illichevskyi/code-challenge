package com.hsbc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserPost implements Serializable {
    private long userId;
    private String userName;
    private String text;
}
