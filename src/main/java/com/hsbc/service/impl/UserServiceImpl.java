package com.hsbc.service.impl;

import com.hsbc.model.entity.User;
import com.hsbc.model.dto.Subscription;
import com.hsbc.repository.UserRepository;
import com.hsbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void followUser(Subscription subscription) {
        User follower = userRepository.findOne(subscription.getFollowerId());
        User followee = userRepository.findOne(subscription.getFolloweeId());

        follower.addFollowee(followee);
    }

    public void unfollowUser(Subscription subscription) {
        User follower = userRepository.findOne(subscription.getFollowerId());
        User followee = userRepository.findOne(subscription.getFolloweeId());

        follower.removeFollowee(followee);
    }

    public Set<User> getFollowers(long userId) {
        User user = userRepository.findOne(userId);
        return user.getFollowers();
    }

    public Set<User> getFollowees(long userId) {
        User user = userRepository.findOne(userId);
        return user.getFollowees();
    }
}
