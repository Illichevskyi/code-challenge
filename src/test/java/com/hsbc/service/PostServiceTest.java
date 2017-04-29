package com.hsbc.service;

import com.hsbc.exception.PostSizeExceedException;
import com.hsbc.exception.UserDoesNotExistException;
import com.hsbc.model.entity.Post;
import com.hsbc.model.entity.User;
import com.hsbc.model.dto.Like;
import com.hsbc.model.dto.UserPost;
import com.hsbc.repository.PostRepository;
import com.hsbc.repository.UserRepository;
import com.hsbc.service.impl.PostServiceImpl;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    private static final int POST_MAX_LENGTH = 140;

    private static User newUser1;
    private static User savedUser1;
    private static UserPost userPost1;
    private static Post newPost1;
    private static Post savedPost1;
    private static List<Post> user1Wall = new ArrayList<>();
    private static List<Post> follower1Timeline = new ArrayList<>();
    private static User follower;

    @BeforeClass
    public static void init() {
        newUser1 = new User("newUser1");
        savedUser1 = new User(1, newUser1.getName());
        userPost1 = new UserPost(savedUser1.getId(), savedUser1.getName(), "text1");
        newPost1 = new Post(userPost1.getText(), savedUser1);
        savedPost1 = new Post(11, newPost1.getText());
        user1Wall.add(savedPost1);

        savedPost1.likePost(savedUser1);

        follower = new User(21, "user1");
        User followee = new User(22, "user2");

        follower.addFollowee(followee);
        followee.addFollower(follower);

        follower1Timeline.add(savedPost1);
    }

    @Test
    public void testSaveValidPostForNewUser() {
        when(userRepository.save(newUser1)).thenReturn(savedUser1);
        when(postRepository.save(newPost1)).thenReturn(savedPost1);

        setField(postService, "postMaxLength", POST_MAX_LENGTH);

        try {
            postService.savePost(userPost1);
        } catch (PostSizeExceedException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSaveValidPostForExistingUser() {
        when(userRepository.findOne(savedUser1.getId())).thenReturn(savedUser1);
        when(postRepository.save(newPost1)).thenReturn(savedPost1);
        setField(postService, "postMaxLength", POST_MAX_LENGTH);

        try {
            postService.savePost(userPost1);
        } catch (PostSizeExceedException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSaveInValidPost() {
        String exceededText = leftPad("", POST_MAX_LENGTH + 1, '*');
        UserPost exceedTextUserPost = new UserPost(savedUser1.getId(), savedUser1.getName(), exceededText);
        try {
            postService.savePost(exceedTextUserPost);
            fail("Exception not thrown");
        } catch (PostSizeExceedException e) {
            assertThat(e).isInstanceOf(PostSizeExceedException.class);
        }
    }

    @Test
    public void testGetWallForExistingUser() {
        when(userRepository.findOne(savedUser1.getId())).thenReturn(savedUser1);
        when(postRepository.getWall(savedUser1)).thenReturn(user1Wall);
        List<Post> wall = postService.getWall(savedUser1.getId());
        assertThat(wall.size()).isEqualTo(1);
        assertThat(wall.get(0)).isEqualTo(savedPost1);
    }

    @Test
    public void testGetWallForNotExistingUser() {
        when(userRepository.findOne(newUser1.getId())).thenReturn(null);
        try {
            postService.getWall(newPost1.getId());
            fail("Exception not thrown");
        } catch (UserDoesNotExistException e) {
            assertThat(e).isInstanceOf(UserDoesNotExistException.class);
        }
    }

    @Test
    public void testGetTimelineForExistingUser() {
        when(userRepository.findOne(follower.getId())).thenReturn(follower);
        when(postRepository.getTimeline(follower.getFollowees())).thenReturn(follower1Timeline);

        List<Post> tileLine = postService.getTimeline(follower.getId());

        assertThat(tileLine.size()).isEqualTo(1);
        assertThat(tileLine.get(0)).isEqualTo(savedPost1);
    }

    @Test
    public void testGetTimelineForNotExistingUser() {
        when(userRepository.findOne(newUser1.getId())).thenReturn(null);
        try {
            postService.getTimeline(follower.getId());
            fail("Exception not thrown");
        } catch (UserDoesNotExistException e) {
            assertThat(e).isInstanceOf(UserDoesNotExistException.class);
        }
    }

    @Test
    public void testLikePost() {
        Like like = new Like(savedUser1.getId(), savedPost1.getId());
        when(userRepository.findOne(savedUser1.getId())).thenReturn(savedUser1);
        when(postRepository.findOne(savedPost1.getId())).thenReturn(savedPost1);

        postService.likePost(like);

        assertThat(savedPost1.getReactedUsers().size()).isEqualTo(1);
        assertThat(savedPost1.getReactedUsers().contains(savedUser1)).isTrue();
    }

    @After
    public void cleanup() {
        reset(userRepository, postRepository);
    }
}
