package com.scumm.loom.dto.user;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class CreateUserDto {
    @Email
    private String email;

    @NotEmpty
    private String username;
}
