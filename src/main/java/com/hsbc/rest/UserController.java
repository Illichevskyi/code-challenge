package com.hsbc.rest;

import com.hsbc.model.entity.User;
import com.hsbc.model.dto.Subscription;
import com.hsbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/follow", method = POST)
    public void followUser(@RequestBody Subscription subscription) {
        userService.followUser(subscription);
    }

    @RequestMapping(value = "/unfollow", method = POST)
    public void unFollowUser(@RequestBody Subscription subscription) {
        userService.unfollowUser(subscription);
    }

    @RequestMapping(value = "/followers/{userId}", method = GET)
    public Set<User> getFollowers(@PathVariable(value = "userId") long userId) {
        return userService.getFollowers(userId);
    }

    @RequestMapping(value = "/followees/{userId}", method = GET)
    public Set<User> getFollowees(@PathVariable(value = "userId") long userId) {
        return userService.getFollowees(userId);
    }
}
