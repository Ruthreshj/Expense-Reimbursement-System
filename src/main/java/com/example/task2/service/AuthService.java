package com.example.task2.service;

import com.example.task2.dto.auth.AuthResponse;
import com.example.task2.dto.auth.LoginRequest;
import com.example.task2.dto.auth.SignupRequest;
import com.example.task2.model.Employee;
import com.example.task2.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse signup(SignupRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }

        Employee employee = Employee.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        Employee saved = employeeRepository.save(employee);

        return new AuthResponse(
                "Signup successful",
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        Employee employee = employeeRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), employee.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return new AuthResponse(
                "Login successful",
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getRole());
    }
}
