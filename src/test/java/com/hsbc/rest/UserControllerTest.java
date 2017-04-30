package com.hsbc.rest;

import com.hsbc.model.dto.Subscription;
import com.hsbc.service.UserService;
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
import java.util.HashSet;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private static MediaType jsonContentType;

    private static String subscriptionContent;

    private static String userURL = "/user";
    private static String followURL = userURL + "/follow";
    private static String unfollowURL = userURL + "/unfollow";
    private static String followersURL = userURL + "/followers/1";
    private static String followeesURL = userURL + "/followees/1";

    @BeforeClass
    public static void init() {
        jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        subscriptionContent = "{\"followerId\":2,\"followeeId\":1}";
    }


    @Before
    public void reinit() {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testFollowUser() throws Exception {
        doNothing().when(userService).followUser(any(Subscription.class));

        mockMvc.perform(post(followURL)
                .content(subscriptionContent)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnfollowUser() throws Exception {
        doNothing().when(userService).unfollowUser(any(Subscription.class));

        mockMvc.perform(post(unfollowURL)
                .content(subscriptionContent)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFollowers() throws Exception {
        when(userService.getFollowers(anyLong())).thenReturn(new HashSet<>());

        mockMvc.perform(get(followersURL)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFollowees() throws Exception {
        when(userService.getFollowees(anyLong())).thenReturn(new HashSet<>());

        mockMvc.perform(get(followeesURL)
                .contentType(jsonContentType))
                .andExpect(status().isOk());
    }
}
