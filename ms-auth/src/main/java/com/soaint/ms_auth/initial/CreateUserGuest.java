package com.soaint.ms_auth.initial;

import com.soaint.ms_auth.model.UserAuth;
import com.soaint.ms_auth.repository.UserAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateUserGuest implements CommandLineRunner {

    @Value("${com.soaint.ms_db.userguest}")
    private String userGuest;
    @Value("${com.soaint.ms_db.pswdguest}")
    private String pswdGuest;

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserGuest(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        UserAuth userAuth = UserAuth.builder()
                .username(userGuest)
                .password(passwordEncoder.encode(pswdGuest))
                .build();
        this.userAuthRepository.save(userAuth);
        log.debug("User guest created: {}", userAuth.getUsername());
    }
}
