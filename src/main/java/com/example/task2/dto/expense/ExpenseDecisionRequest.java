package com.example.task2.dto.expense;

import jakarta.validation.constraints.Size;

public record ExpenseDecisionRequest(
        @Size(max = 500) String remarks
) {
}
