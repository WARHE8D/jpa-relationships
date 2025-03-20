package com.warhe8d.service;

import com.warhe8d.exception.LibraryExceptions;
import com.warhe8d.models.Users;
import com.warhe8d.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepo userRepo;

    public UsersServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public Users findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
                ()-> new LibraryExceptions("UserName not found")
        );
    }
}
