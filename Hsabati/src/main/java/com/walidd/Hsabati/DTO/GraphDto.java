package com.walidd.Hsabati.DTO;

import java.util.List;

import com.walidd.Hsabati.Entity.Expense;
import com.walidd.Hsabati.Entity.Income;
import lombok.Data;


@Data
public class GraphDto {
    

    List<Income> incomesList;
    List<Expense> expensesList;
}
