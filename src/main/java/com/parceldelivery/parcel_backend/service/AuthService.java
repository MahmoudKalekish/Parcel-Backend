package com.parceldelivery.parcel_backend.service;


import com.parceldelivery.parcel_backend.dto.AuthRequest;
import com.parceldelivery.parcel_backend.dto.AuthResponse;
import com.parceldelivery.parcel_backend.entity.User;
import com.parceldelivery.parcel_backend.repository.UserRepository;
import com.parceldelivery.parcel_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponse(jwtService.generateToken(user.getUsername()));
    }

    public AuthResponse register(AuthRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        return new AuthResponse(jwtService.generateToken(user.getUsername()));
    }
}