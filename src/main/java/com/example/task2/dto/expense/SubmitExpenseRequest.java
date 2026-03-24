package com.example.task2.dto.expense;

import com.example.task2.model.enums.ExpenseCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SubmitExpenseRequest(
        @NotNull @Positive Double amount,
        @NotNull ExpenseCategory category,
        @Size(max = 500) String remarks
) {
}
