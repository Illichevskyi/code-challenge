package com.hsbc.model;

import com.hsbc.model.entity.Post;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String TEXT_1 = "text1";

    @Test
    public void equality() {
        Post post1 = new Post(ID_1, TEXT_1);
        Post post2 = new Post(ID_1, TEXT_1);
        Post post3 = new Post(ID_2, TEXT_1);

        assertThat(post1.getId()).isEqualTo(post2.getId());
        assertThat(post1.getId()).isNotEqualTo(post3.getId());
    }
}