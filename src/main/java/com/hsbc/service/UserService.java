package com.hsbc.service;

import com.hsbc.model.entity.User;
import com.hsbc.model.dto.Subscription;

import java.util.Set;

public interface UserService {

    void followUser(Subscription subscription);

    void unfollowUser(Subscription subscription);

    Set<User> getFollowers(long userId);

    Set<User> getFollowees(long userId);
}
