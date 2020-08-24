package com.garwan.eshop.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class CreateUserRequest {
    @NotBlank
    @Length(min = 3, max = 255)
    private String username;
    @NotBlank
    @Length(min = 5, max = 255)
    private String password;
    @NotBlank
    @Email
    @Length(max = 255)
    private String email;
}
