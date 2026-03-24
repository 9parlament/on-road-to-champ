package com.parlament.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualPasswords
public class RegistrationForm {
    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers allowed")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, message = "Password must be at least 3 characters long")
    @Pattern(regexp = "^\\S+$", message = "Password cannot contain whitespace")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String passwordSubmit;
}
