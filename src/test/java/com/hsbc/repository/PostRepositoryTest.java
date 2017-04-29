package com.hsbc.repository;

import com.hsbc.Application;
import com.hsbc.model.entity.Post;
import com.hsbc.model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})

public class PostRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    private static User user;

    @Before
    public void init() {
        user = new User("name1");
        userRepository.save(user);
    }

    @Test
    public void saveNewPost() {
        String text = "text1";
        Post post = new Post(text, user);

        postRepository.save(post);

        List<Post> foundPostList = postRepository.findAll();
        assertThat(foundPostList.size()).isEqualTo(1);
        assertThat(foundPostList.get(0).getText()).isEqualTo(text);
    }

    @Test
    public void deletePost() {
        String text = "text1";
        Post post = new Post(text, user);

        postRepository.save(post);
        postRepository.delete(post.getId());

        List<Post> foundPostList = postRepository.findAll();
        assertThat(foundPostList.size()).isEqualTo(0);
    }

    @Test
    public void updatePost() {
        String text = "text1";
        Post post = new Post(text, user);

        postRepository.save(post);

        String updatePostText = "updatePostText1";

        post.setText(updatePostText);

        postRepository.save(post);

        List<Post> foundPostList = postRepository.findAll();
        assertThat(foundPostList.size()).isEqualTo(1);
        assertThat(foundPostList.get(0).getText()).isEqualTo(updatePostText);
    }

    @Test
    public void exceedMaxTextSize() {
        String exceededText = leftPad("", 141, '*');
        Post post = new Post(exceededText, user);
        try {
            postRepository.save(post);
            fail("Exception not thrown");
        } catch (ConstraintViolationException e) {
            assertThat(e).isInstanceOf(ConstraintViolationException.class)
                    .hasMessageContaining("Post cannot be longer than 140 characters");
        }
    }

    @Test
    public void testGetWall() {
        String text0 = "text0";
        String text1 = "text1";
        String text2 = "text2";
        Post post0 = new Post(text0, user);
        Post post1 = new Post(text1, user);
        Post post2 = new Post(text2, user);

        postRepository.save(post0);
        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> foundPostList = postRepository.getWall(user);

        assertThat(foundPostList.size()).isEqualTo(3);
        assertThat(foundPostList.get(0).getText()).isEqualTo(text2);
        assertThat(foundPostList.get(1).getText()).isEqualTo(text1);
        assertThat(foundPostList.get(2).getText()).isEqualTo(text0);

    }

    @Test
    public void testGetTimeline() {
        String text0 = "text0";
        String text1 = "text1";

        Post post0 = new Post(text0, user);
        Post post1 = new Post(text1, user);

        postRepository.save(post0);
        postRepository.save(post1);

        User follower = new User("follower1");
        follower.addFollowee(user);

        userRepository.save(follower);

        List<Post> followerTimeline = postRepository.getTimeline(follower.getFollowees());
        List<Post> followeeTimeline = postRepository.getTimeline(user.getFollowees());

        assertThat(followerTimeline.size()).isEqualTo(2);
        assertThat(followerTimeline.get(0).getText()).isEqualTo(text1);
        assertThat(followerTimeline.get(1).getText()).isEqualTo(text0);
        assertThat(followeeTimeline.size()).isEqualTo(0);

    }

    @After
    public void cleanup() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }
}
