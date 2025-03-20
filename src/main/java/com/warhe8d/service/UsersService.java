package com.warhe8d.service;


import com.warhe8d.models.Users;

import java.util.Optional;

public interface UsersService {
    Users findByUsername(String username);
}
