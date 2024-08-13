package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.models.Role;
import com.effective.mobile.tasks.models.User;
import com.effective.mobile.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * создание пользователя
     *
     * @param user пользователь, которого мы хотим создать
     * @return сохраненный пользователь
     */
    @Override
    public User createUser(User user) {
        if(userRepository.existsUserByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if(userRepository.existsUserByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return userRepository.save(user);
    }

    /**
     * поиск по email
     *
     * @param email email пользователя
     * @return найденный пользователь
     */
    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }
        return optionalUser.get();
    }

    /**
     * поиск по username
     *
     * @param username имя пользователя
     * @return найденный пользователь
     */
    @Override
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        User user = getCurrentUser();
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }
}
