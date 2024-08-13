package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.models.User;

public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    User getCurrentUser();
}
