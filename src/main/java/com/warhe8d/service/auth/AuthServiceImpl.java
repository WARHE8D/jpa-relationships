package com.warhe8d.service.auth;

import com.warhe8d.config.JwtConfig;
import com.warhe8d.models.Users;
import com.warhe8d.repo.UserRepo;
import com.warhe8d.requestbody.AuthReqBody;
import com.warhe8d.requestbody.AuthResponse;
import com.warhe8d.requestbody.RegisterReqBody;
import com.warhe8d.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder pEnc;
    private final JwtConfig jwtConfig;
    private final AuthenticationManager authManager;


    @Override
    public AuthResponse register(RegisterReqBody request) {
        var user = Users.builder()
                .username(request.getUsername())
                .password(pEnc.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepo.save(user);

        String token = jwtConfig.generateToken(user);

        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthReqBody request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername())
                .orElseThrow();
        String token = jwtConfig.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
