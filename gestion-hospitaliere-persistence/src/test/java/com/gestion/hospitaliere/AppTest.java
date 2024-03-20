package com.gestion.hospitaliere;


import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.repo.StubUserEntryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit test for simple App.
 */
public class AppTest
{


    StubUserEntryRepo stubUserEntryRepo;
    Persistence persistence;
    Class<User> user;
    @BeforeEach
    void init() throws ClassNotFoundException {
        persistence = new Persistence();
        user = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        stubUserEntryRepo = new StubUserEntryRepo(user);
    }

    @Test
    void registerUser() throws InstantiationException, IllegalAccessException {
        User userToSave = new User();
        userToSave.setEmail("user@gmail.com");
        User savedUser = stubUserEntryRepo.save(userToSave);
        assertNull(savedUser);
    }
}
