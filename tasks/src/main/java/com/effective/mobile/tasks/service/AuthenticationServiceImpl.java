package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.dto.JwtAuthenticationResponse;
import com.effective.mobile.tasks.dto.SignInRequest;
import com.effective.mobile.tasks.dto.SignUpRequest;
import com.effective.mobile.tasks.models.Role;
import com.effective.mobile.tasks.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userService.createUser(user);
        String token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }

    /**
     * Авторизация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
