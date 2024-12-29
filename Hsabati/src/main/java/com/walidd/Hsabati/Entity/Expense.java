package com.walidd.Hsabati.Entity;

import java.time.LocalDate;

import com.walidd.Hsabati.DTO.ExpenseDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer amount;

    private LocalDate date;
    private String category;

    public ExpenseDto getExpenseDTO(){
        ExpenseDto expenseDTO = new ExpenseDto();
        expenseDTO.setId(id);
        expenseDTO.setAmount(amount);
        expenseDTO.setTitle(title);
        expenseDTO.setCategory(category);
        expenseDTO.setDescription(description);
        expenseDTO.setDate(date);
        return expenseDTO;
    }
    
}
