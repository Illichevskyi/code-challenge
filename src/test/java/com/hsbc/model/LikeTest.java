package com.hsbc.model;

import com.hsbc.model.dto.Like;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LikeTest {

    private static final long LIKED_USER_ID_1 = 1L;
    private static final long LIKED_USER_ID_2 = 2L;
    private static final long POST_ID_1 = 11L;
    private static final long POST_ID_2 = 12L;

    @Test
    public void equality() {
        Like like1 = new Like(LIKED_USER_ID_1, POST_ID_1);
        Like like2 = new Like(LIKED_USER_ID_1, POST_ID_1);
        Like like3 = new Like(LIKED_USER_ID_1, POST_ID_2);
        Like like4 = new Like(LIKED_USER_ID_2, POST_ID_1);
        Like like5 = new Like(LIKED_USER_ID_2, POST_ID_2);

        assertThat(like1).isEqualTo(like2);
        assertThat(like1).isNotEqualTo(like3);
        assertThat(like1).isNotEqualTo(like3);
        assertThat(like1).isNotEqualTo(like4);
        assertThat(like1).isNotEqualTo(like5);
        assertThat(like2).isNotEqualTo(like3);
        assertThat(like2).isNotEqualTo(like4);
        assertThat(like2).isNotEqualTo(like5);
        assertThat(like3).isNotEqualTo(like4);
        assertThat(like3).isNotEqualTo(like5);
        assertThat(like4).isNotEqualTo(like5);
    }
}