package com.scumm.loom.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    @Email
    private String email;

    @NotEmpty
    private String username;
}
