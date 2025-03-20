package com.warhe8d.service;

import com.warhe8d.requestbody.AuthReqBody;
import com.warhe8d.requestbody.AuthResponse;
import com.warhe8d.requestbody.RegisterReqBody;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse register(RegisterReqBody request) {
        return null;
    }

    @Override
    public AuthResponse authenticate(AuthReqBody request) {
        return null;
    }
}
