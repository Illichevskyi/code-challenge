package com.hsbc.model;

import com.hsbc.model.dto.Subscription;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionTest {

    private static final long FOLLOWER_ID_1 = 1L;
    private static final long FOLLOWER_ID_2 = 2L;
    private static final long FOLLOWEE_ID_1 = 11L;
    private static final long FOLLOWEE_ID_2 = 12L;

    @Test
    public void equality() {
        Subscription subscription1 = new Subscription(FOLLOWER_ID_1, FOLLOWEE_ID_1);
        Subscription subscription2 = new Subscription(FOLLOWER_ID_1, FOLLOWEE_ID_1);
        Subscription subscription3 = new Subscription(FOLLOWER_ID_1, FOLLOWEE_ID_2);
        Subscription subscription4 = new Subscription(FOLLOWER_ID_2, FOLLOWEE_ID_1);
        Subscription subscription5 = new Subscription(FOLLOWER_ID_2, FOLLOWEE_ID_2);

        assertThat(subscription1).isEqualTo(subscription2);
        assertThat(subscription1).isNotEqualTo(subscription3);
        assertThat(subscription1).isNotEqualTo(subscription3);
        assertThat(subscription1).isNotEqualTo(subscription4);
        assertThat(subscription1).isNotEqualTo(subscription5);
        assertThat(subscription2).isNotEqualTo(subscription3);
        assertThat(subscription2).isNotEqualTo(subscription4);
        assertThat(subscription2).isNotEqualTo(subscription5);
        assertThat(subscription3).isNotEqualTo(subscription4);
        assertThat(subscription3).isNotEqualTo(subscription5);
        assertThat(subscription4).isNotEqualTo(subscription5);
    }
}