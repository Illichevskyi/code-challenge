package com.hsbc.model;

import com.hsbc.model.dto.UserPost;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPostTest {

    private static final long USER_ID_1 = 1L;
    private static final long USER_ID_2 = 2L;
    private static final String USER_NAME_1 = "name1";
    private static final String TEXT_1 = "text1";

    @Test
    public void equality() {
        UserPost userPost1 = new UserPost(USER_ID_1, USER_NAME_1, TEXT_1);
        UserPost userPost2 = new UserPost(USER_ID_1, USER_NAME_1, TEXT_1);
        UserPost userPost3 = new UserPost(USER_ID_1, TEXT_1, TEXT_1);
        UserPost userPost4 = new UserPost(USER_ID_2, USER_NAME_1, TEXT_1);
        UserPost userPost5 = new UserPost(USER_ID_2, TEXT_1, TEXT_1);

        assertThat(userPost1).isEqualTo(userPost2);
        assertThat(userPost1).isNotEqualTo(userPost3);
        assertThat(userPost1).isNotEqualTo(userPost3);
        assertThat(userPost1).isNotEqualTo(userPost4);
        assertThat(userPost1).isNotEqualTo(userPost5);
        assertThat(userPost2).isNotEqualTo(userPost3);
        assertThat(userPost2).isNotEqualTo(userPost4);
        assertThat(userPost2).isNotEqualTo(userPost5);
        assertThat(userPost3).isNotEqualTo(userPost4);
        assertThat(userPost3).isNotEqualTo(userPost5);
        assertThat(userPost4).isNotEqualTo(userPost5);
    }
}