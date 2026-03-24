package com.example.task2.dto.expense;

import com.example.task2.model.enums.ExpenseCategory;
import com.example.task2.model.enums.ExpenseStatus;
import java.time.LocalDateTime;

public record ExpenseResponse(
        Long id,
        Double amount,
        ExpenseCategory category,
        ExpenseStatus status,
        Long submittedBy,
        Long approvedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String remarks
) {
}
