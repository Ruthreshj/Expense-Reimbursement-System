package com.example.task2.repository;

import com.example.task2.model.Expense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findBySubmittedByIdOrderByCreatedAtDesc(Long submittedById);

    List<Expense> findAllByOrderByCreatedAtDesc();
}
