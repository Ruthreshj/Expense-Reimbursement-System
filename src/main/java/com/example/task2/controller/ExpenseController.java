package com.example.task2.controller;

import com.example.task2.dto.expense.ExpenseResponse;
import com.example.task2.dto.expense.SubmitExpenseRequest;
import com.example.task2.service.ExpenseService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse submitExpense(@Valid @RequestBody SubmitExpenseRequest request,
                                         Authentication authentication) {
        return expenseService.submitExpense(authentication.getName(), request);
    }

    @GetMapping
    public List<ExpenseResponse> getMyExpenses(Authentication authentication) {
        return expenseService.getMyExpenses(authentication.getName());
    }
}
