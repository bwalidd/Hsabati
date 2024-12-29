package com.walidd.Hsabati.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walidd.Hsabati.Entity.Expense;
@Repository
public interface ExpenseRepos extends JpaRepository<Expense, Long> {
     List<Expense> findByDateBetween(LocalDate start, LocalDate end);

     @Query("SELECT SUM(e.amount) FROM Expense e")
    Integer getSumExpense();

    Optional <Expense> findFirstByOrderByDateDesc();
}
