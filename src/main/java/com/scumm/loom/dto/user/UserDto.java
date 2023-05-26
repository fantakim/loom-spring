package com.scumm.loom.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private long id;
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
