package com.hsbc.model;

import com.hsbc.model.dto.UserRepost;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepostTest {

    private static final long USER_ID_1 = 1L;
    private static final long USER_ID_2 = 2L;
    private static final long POST_ID_1 = 11L;
    private static final long POST_ID_2 = 12L;

    @Test
    public void equality() {
        UserRepost userRepost1 = new UserRepost(USER_ID_1, POST_ID_1);
        UserRepost userRepost2 = new UserRepost(USER_ID_1, POST_ID_1);
        UserRepost userRepost3 = new UserRepost(USER_ID_1, POST_ID_2);
        UserRepost userRepost4 = new UserRepost(USER_ID_2, POST_ID_1);
        UserRepost userRepost5 = new UserRepost(USER_ID_2, POST_ID_2);

        assertThat(userRepost1).isEqualTo(userRepost2);
        assertThat(userRepost1).isNotEqualTo(userRepost3);
        assertThat(userRepost1).isNotEqualTo(userRepost3);
        assertThat(userRepost1).isNotEqualTo(userRepost4);
        assertThat(userRepost1).isNotEqualTo(userRepost5);
        assertThat(userRepost2).isNotEqualTo(userRepost3);
        assertThat(userRepost2).isNotEqualTo(userRepost4);
        assertThat(userRepost2).isNotEqualTo(userRepost5);
        assertThat(userRepost3).isNotEqualTo(userRepost4);
        assertThat(userRepost3).isNotEqualTo(userRepost5);
        assertThat(userRepost4).isNotEqualTo(userRepost5);
    }
}