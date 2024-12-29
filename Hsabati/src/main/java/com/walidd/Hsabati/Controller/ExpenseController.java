package com.walidd.Hsabati.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walidd.Hsabati.DTO.ExpenseDto;
import com.walidd.Hsabati.Entity.Expense;
import com.walidd.Hsabati.Service.Expense.ExpenseSrvImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {
    
    private final ExpenseSrvImpl expenseService;


    @PostMapping
    public ResponseEntity<?> saveExpense(@RequestBody ExpenseDto expenseDto) {
        Expense createdexpense = expenseService.PostSrv(expenseDto);

        if (createdexpense != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdexpense);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> EditExpenseById(@PathVariable Long id, @RequestBody ExpenseDto dto) {
        try{
            return ResponseEntity.ok(expenseService.updateExpenseSrv(id,dto));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteExpense(@PathVariable Long id)
    {
        try{
            expenseService.DeleteExpense(id);
            return ResponseEntity.ok(null);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
