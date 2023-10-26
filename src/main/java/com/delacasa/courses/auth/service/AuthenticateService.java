package com.delacasa.courses.auth.service;

import com.delacasa.courses.auth.entity.AppUser;
import com.delacasa.courses.auth.model.CustomAuthToken;
import com.delacasa.courses.auth.model.CustomPrincipal;
import com.delacasa.courses.auth.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final AppUserRepo userRepo;
    private final PasswordEncoder passEncoder;


    public Optional<AppUser> canAuthenticate(String username, String password) {
        final Optional<AppUser> user = userRepo.findByUsername(username);
        return user.filter(appUser -> passEncoder.matches(password, appUser.getPassword()));
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    public CustomAuthToken setUpAuthToken(AppUser user) {
        return new CustomAuthToken(new CustomPrincipal(user), null);
    }


}
