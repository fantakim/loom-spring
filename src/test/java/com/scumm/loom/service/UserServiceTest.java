package com.scumm.loom.service;

import com.scumm.loom.dto.user.CreateUserDto;
import com.scumm.loom.dto.user.UpdateUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan(basePackages = "com.scumm.loom.")
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void createAndUpdate() {
        // create
        var createDto = new CreateUserDto("test-email", "test-username");
        var user = userService.create(createDto);

        // reload
        user = userService.find(user.getId());
        assertEquals("test-email", user.getEmail());
        assertEquals("test-username", user.getUsername());

        // update
        var updateDto = new UpdateUserDto("email_updated", "username-updated");
        user = userService.update(user.getId(), updateDto);

        user = userService.find(user.getId());

        assertEquals("username-updated", user.getUsername());
    }
}
