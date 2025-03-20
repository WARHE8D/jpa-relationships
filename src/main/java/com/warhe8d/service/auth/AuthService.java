package com.warhe8d.service.auth;

import com.warhe8d.requestbody.AuthReqBody;
import com.warhe8d.requestbody.AuthResponse;
import com.warhe8d.requestbody.RegisterReqBody;

public interface AuthService {
    AuthResponse register(RegisterReqBody request);

    AuthResponse authenticate(AuthReqBody request);
}
