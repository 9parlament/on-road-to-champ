package com.parlament.service;

import com.parlament.model.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public void createUser(RegistrationForm registrationForm) {
        UserDetails user = User.builder()
                .username(registrationForm.getUsername())
                .password(registrationForm.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .authorities("read", "write")
                .build();
        userDetailsManager.createUser(user);
    }

    public boolean isUserExist(String username) {
        return userDetailsManager.userExists(username);
    }
}
