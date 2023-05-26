package com.scumm.loom.dto.user;

import com.scumm.loom.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserDtoTest {

    @Spy
    private ModelMapper mapper;

    @Test
    public void map() {
        var user = new User("test-email", "test-username");
        var dto = mapper.map(user, UserDto.class);

        assertEquals("test-email", dto.getEmail());
        assertEquals("test-username", dto.getUsername());
    }
}