package com.example.task2.controller;

import com.example.task2.dto.expense.ExpenseDecisionRequest;
import com.example.task2.dto.expense.ExpenseResponse;
import com.example.task2.service.ExpenseService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/expenses")
@RequiredArgsConstructor
public class AdminExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public List<ExpenseResponse> getAllSubmittedExpenses(Authentication authentication) {
        return expenseService.getAllExpensesForAdmin(authentication.getName());
    }

    @PatchMapping("/{expenseId}/approve")
    public ExpenseResponse approveExpense(@PathVariable Long expenseId,
                                          @Valid @RequestBody(required = false) ExpenseDecisionRequest request,
                                          Authentication authentication) {
        return expenseService.approveExpense(authentication.getName(), expenseId, request);
    }

    @PatchMapping("/{expenseId}/reject")
    public ExpenseResponse rejectExpense(@PathVariable Long expenseId,
                                         @Valid @RequestBody ExpenseDecisionRequest request,
                                         Authentication authentication) {
        return expenseService.rejectExpense(authentication.getName(), expenseId, request);
    }
}
