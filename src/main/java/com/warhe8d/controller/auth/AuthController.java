package com.warhe8d.controller.auth;

import com.warhe8d.requestbody.AuthReqBody;
import com.warhe8d.requestbody.AuthResponse;
import com.warhe8d.requestbody.RegisterReqBody;
import com.warhe8d.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lib/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterReqBody registerReqBody) {
        return ResponseEntity.ok(authService.register(registerReqBody));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthReqBody authReqBody) {
        return ResponseEntity.ok(authService.authenticate(authReqBody));
    }
}
