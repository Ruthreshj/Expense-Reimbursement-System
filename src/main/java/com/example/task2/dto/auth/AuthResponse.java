package com.example.task2.dto.auth;

import com.example.task2.model.enums.EmployeeRole;

public record AuthResponse(
        String message,
        Long employeeId,
        String name,
        String email,
        EmployeeRole role
) {
}
