package com.example.task2.service;

import com.example.task2.dto.expense.ExpenseResponse;
import com.example.task2.dto.expense.SubmitExpenseRequest;
import com.example.task2.model.Employee;
import com.example.task2.model.Expense;
import com.example.task2.model.enums.EmployeeRole;
import com.example.task2.model.enums.ExpenseStatus;
import com.example.task2.repository.EmployeeRepository;
import com.example.task2.repository.ExpenseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final EmployeeRepository employeeRepository;

    public ExpenseResponse submitExpense(String authenticatedEmail, SubmitExpenseRequest request) {
        Employee employee = getAuthenticatedEmployee(authenticatedEmail);

        Expense expense = Expense.builder()
                .amount(request.amount())
                .category(request.category())
                .status(ExpenseStatus.PENDING)
                .submittedBy(employee)
                .remarks(request.remarks())
                .build();

        Expense savedExpense = expenseRepository.save(expense);
        return toResponse(savedExpense);
    }

    public List<ExpenseResponse> getMyExpenses(String authenticatedEmail) {
        Employee employee = getAuthenticatedEmployee(authenticatedEmail);

        return expenseRepository.findBySubmittedByIdOrderByCreatedAtDesc(employee.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Employee getAuthenticatedEmployee(String authenticatedEmail) {
        Employee employee = employeeRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user not found"));

        if (employee.getRole() != EmployeeRole.EMPLOYEE) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only employees can submit and view personal expenses");
        }

        return employee;
    }

    private ExpenseResponse toResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getStatus(),
                expense.getSubmittedBy().getId(),
                expense.getApprovedBy() != null ? expense.getApprovedBy().getId() : null,
                expense.getCreatedAt(),
                expense.getUpdatedAt(),
                expense.getRemarks());
    }
}
