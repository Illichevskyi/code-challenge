package com.hsbc.model;

import com.hsbc.model.entity.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String NAME_1 = "name1";

    @Test
    public void equality() {
        User user1 = new User(ID_1, NAME_1);
        User user2 = new User(ID_1, NAME_1);
        User user3 = new User(ID_2, NAME_1);

        assertThat(user1).isEqualTo(user2);
        assertThat(user1).isNotEqualTo(user3);
    }
}