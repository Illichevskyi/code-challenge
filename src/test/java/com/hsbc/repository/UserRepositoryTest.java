package com.hsbc.repository;

import com.hsbc.Application;
import com.hsbc.model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User user;
    private static String userName = "name1";

    @Before
    public void init() {
        user = new User(userName);
    }

    @Test
    public void saveNewUser() {

        userRepository.save(user);

        List<User> foundUserList = userRepository.findAll();
        assertThat(foundUserList.size()).isEqualTo(1);
        assertThat(foundUserList.get(0).getName()).isEqualTo(userName);
    }

    @Test
    public void deleteUser() {
        userRepository.save(user);
        userRepository.delete(user);

        List<User> foundUserList = userRepository.findAll();
        assertThat(foundUserList.size()).isEqualTo(0);
    }

    @Test
    public void updateUser() {

        userRepository.save(user);
        String updatedUserName = "updatedUserName1";
        user.setName(updatedUserName);
        userRepository.save(user);

        List<User> foundUserList = userRepository.findAll();
        assertThat(foundUserList.size()).isEqualTo(1);
        assertThat(foundUserList.get(0).getName()).isEqualTo(updatedUserName);
    }

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }
}
