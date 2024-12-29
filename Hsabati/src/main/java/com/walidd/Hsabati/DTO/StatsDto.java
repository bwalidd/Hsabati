package com.walidd.Hsabati.DTO;

import com.walidd.Hsabati.Entity.Expense;
import com.walidd.Hsabati.Entity.Income;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatsDto {
    private Integer totalIncome;
    private Integer totalExpense;

    private Income lastIncome;
    private Expense lastExpense;

    private Integer Balance;
    private Integer minIncome;
    private Integer maxIncome;
    private Integer maxExpense;
    private Integer minExpense;
}
