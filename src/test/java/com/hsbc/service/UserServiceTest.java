package com.hsbc.service;

import com.hsbc.model.entity.User;
import com.hsbc.model.dto.Subscription;
import com.hsbc.repository.UserRepository;
import com.hsbc.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private static User follower;
    private static User followee;
    private static User followerWithFollowees;
    private static User followeeWithFollowers;


    @BeforeClass
    public static void initUsers() {
        follower = new User(1, "user1");
        followee = new User(2, "user2");

        followerWithFollowees = new User(3, "user3");
        followeeWithFollowers = new User(4, "user4");
    }

    @Before
    public void reinitSubscription() {
        followerWithFollowees.addFollowee(followeeWithFollowers);
        followeeWithFollowers.addFollower(followerWithFollowees);
    }

    @Test
    public void testFollowUser() {
        when(userRepository.findOne(follower.getId())).thenReturn(follower);
        when(userRepository.findOne(followee.getId())).thenReturn(followee);

        Subscription followSubscription = createFollowSubscription();
        userService.followUser(followSubscription);

        assertThat(follower.getFollowees().size()).isEqualTo(1);
        assertThat(follower.getFollowees().contains(followee)).isTrue();
    }

    @Test
    public void testUnfollowUser() {
        when(userRepository.findOne(followerWithFollowees.getId())).thenReturn(followerWithFollowees);
        when(userRepository.findOne(followeeWithFollowers.getId())).thenReturn(followeeWithFollowers);

        Subscription unfollowSubscription = createUnfollowSubscription();
        userService.unfollowUser(unfollowSubscription);

        assertThat(followerWithFollowees.getFollowees().size()).isEqualTo(0);
    }

    @Test
    public void testGetFollowers() {
        when(userRepository.findOne(followeeWithFollowers.getId())).thenReturn(followeeWithFollowers);
        Set<User> followers = userService.getFollowers(followeeWithFollowers.getId());

        assertThat(followers.size()).isEqualTo(1);
        assertThat(followers.contains(followerWithFollowees)).isTrue();
    }

    @Test
    public void testGetFollowees() {
        when(userRepository.findOne(followerWithFollowees.getId())).thenReturn(followerWithFollowees);
        Set<User> followees = userService.getFollowees(followerWithFollowees.getId());

        assertThat(followees.size()).isEqualTo(1);
        assertThat(followees.contains(followeeWithFollowers)).isTrue();
    }

    private Subscription createFollowSubscription() {
        return new Subscription(follower.getId(), followee.getId());
    }

    private Subscription createUnfollowSubscription() {
        return new Subscription(followerWithFollowees.getId(), followeeWithFollowers.getId());
    }

    @After
    public void cleanup() {
        reset(userRepository);
    }
}
