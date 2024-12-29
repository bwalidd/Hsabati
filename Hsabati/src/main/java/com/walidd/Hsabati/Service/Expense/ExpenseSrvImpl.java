package com.walidd.Hsabati.Service.Expense;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.stereotype.Service;

import com.walidd.Hsabati.DTO.ExpenseDto;
import com.walidd.Hsabati.Entity.Expense;
import com.walidd.Hsabati.Repository.ExpenseRepos;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseSrvImpl implements ExpenseService {
    

    public final ExpenseRepos expenseRepos;

    public Expense PostSrv(ExpenseDto expenseDto) {
        return saveOrUpdateExpense(new Expense(),expenseDto);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto) {
        expense.setTitle(expenseDto.getTitle());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        return expenseRepos.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepos.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepos.findById(id);

        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new EntityNotFoundException("no expense found with id: " + id);
        }
    }

    public Expense updateExpenseSrv(Long id,ExpenseDto expenseDto){
        
        Optional<Expense> optionalExpense = expenseRepos.findById(id);
        if (optionalExpense.isPresent()){
            return saveOrUpdateExpense(optionalExpense.get(), expenseDto);
        }
        throw new EntityNotFoundException("expnese not found with id " + id);
    }

    public void DeleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepos.findById(id);
        if (optionalExpense.isPresent()){
            expenseRepos.deleteById(id);
        }else{
            throw new EntityNotFoundException("expnese not found with id " + id);
        }
    }
}
