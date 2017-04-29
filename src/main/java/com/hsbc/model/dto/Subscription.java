package com.hsbc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Subscription implements Serializable {
    private long followerId;
    private long followeeId;
}
