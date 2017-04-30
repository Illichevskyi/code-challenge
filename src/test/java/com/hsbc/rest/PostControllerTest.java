package com.hsbc.rest;

import com.hsbc.model.dto.Like;
import com.hsbc.model.dto.UserPost;
import com.hsbc.model.dto.UserRepost;
import com.hsbc.service.PostService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    private static MediaType jsonContentType;

    private static String userPostContentForNewUser;
    private static String userPostContentForExistingUser;
    private static String userRepostContent;
    private static String likeContent;

    private static String postURL = "/post";
    private static String repostURL = postURL + "/repost";
    private static String deleteURL = postURL + "/delete/1";
    private static String wallURL = postURL + "/wall/1";
    private static String timelineURL = postURL + "/timeline/1";
    private static String likePostURL = postURL + "/like";
    private static String unlikePostURL = postURL + "/unlike";

    @BeforeClass
    public static void init() {
        jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
        userPostContentForNewUser = "{\"userName\": \"name1\", \"text\":\"1\"}";
        userPostContentForExistingUser = "{\"userId\":1, \"userName\": \"name1\", \"text\":\"1-2\"}";
        userRepostContent = "{\"userId\": 2, \"postId\":1}";
        likeContent = "{\"likedUserId\":2, \"postId\":1}";
    }


    @Before
    public void reinit() {
        mockMvc = standaloneSetup(postController).build();
    }

    @Test
    public void testSavePostForNewUser() throws Exception {
        doNothing().when(postService).savePost(any(UserPost.class));

        mockMvc.perform(post(postURL)
                .content(userPostContentForNewUser)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePostForExistingUser() throws Exception {
        doNothing().when(postService).savePost(any(UserPost.class));

        mockMvc.perform(post(postURL)
                .content(userPostContentForExistingUser)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveRepost() throws Exception {
        doNothing().when(postService).saveRepost(any(UserRepost.class));

        mockMvc.perform(post(repostURL)
                .content(userRepostContent)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        doNothing().when(postService).deletePost(anyLong());

        mockMvc.perform(delete(deleteURL)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWall() throws Exception {
        when(postService.getWall(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(wallURL)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTimeline() throws Exception {
        when(postService.getTimeline(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(timelineURL)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testLikePost() throws Exception {
        doNothing().when(postService).likePost(any(Like.class));

        mockMvc.perform(post(likePostURL)
                .content(likeContent)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnlikePost() throws Exception {
        doNothing().when(postService).unlikePost(any(Like.class));

        mockMvc.perform(post(unlikePostURL)
                .content(likeContent)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }
}
