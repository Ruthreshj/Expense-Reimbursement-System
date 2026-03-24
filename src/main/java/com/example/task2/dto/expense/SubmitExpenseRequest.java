package com.example.task2.dto.expense;

import com.example.task2.model.enums.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SubmitExpenseRequest(
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than 0")
        @Schema(example = "1200.50")
        Double amount,

        @NotNull(message = "Category is required. Allowed values: FOOD, TRAVEL, OFFICE")
        @Schema(example = "TRAVEL", allowableValues = {"FOOD", "TRAVEL", "OFFICE"})
        ExpenseCategory category,

        @Schema(example = "Taxi fare to client office")
        @Size(max = 500) String remarks
) {
}
