package org.reelskill.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.reelskill.data.UserRepository;
import org.reelskill.models.Deck;
import org.reelskill.models.Role;
import org.reelskill.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Bean
    static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldCreateUser()  {
        when(repository.createUser(any(User.class))).thenReturn(new User(0, "user2@example.com", "user2", "password2", Role.USER)); // Mocking to return true on successful creation

        User user = new User();
//        user.setUserId(2);
        user.setEmailAddress("user2@example.com");
        user.setUsername("user2");
        user.setPassword("password2");
        user.setRole(Role.USER);

        Result<User> actual = service.createUser(user);

        assertTrue(actual.isSuccess());
    }
}