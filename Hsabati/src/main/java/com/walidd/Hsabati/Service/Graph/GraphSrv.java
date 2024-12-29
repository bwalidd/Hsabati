package com.walidd.Hsabati.Service.Graph;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.stereotype.Service;

import com.walidd.Hsabati.DTO.GraphDto;
import com.walidd.Hsabati.DTO.StatsDto;
import com.walidd.Hsabati.Entity.Expense;
import com.walidd.Hsabati.Entity.Income;
import com.walidd.Hsabati.Repository.ExpenseRepos;
import com.walidd.Hsabati.Repository.IncomeRepos;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphSrv {
    private final IncomeRepos incomeRepos;
    private final ExpenseRepos expenseRepos;    

    public GraphDto getGraphData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        GraphDto graphDto = new GraphDto();
        graphDto.setExpensesList(expenseRepos.findByDateBetween(startDate, endDate));
        graphDto.setIncomesList(incomeRepos.findByDateBetween(startDate, endDate));

        return graphDto;
    }


    public StatsDto getStats() {
        StatsDto statsDto = new StatsDto();
        statsDto.setTotalIncome(incomeRepos.getSumIncome());
        statsDto.setTotalExpense(expenseRepos.getSumExpense());

        Optional <Income> optIncome = incomeRepos.findFirstByOrderByDateDesc();
        Optional <Expense> optExpense = expenseRepos.findFirstByOrderByDateDesc();


        if(optIncome.isPresent()){
            statsDto.setLastIncome(optIncome.get());
        }
        if(optExpense.isPresent()){
            statsDto.setLastExpense(optExpense.get());
        }


        List<Income> IncomeArea= incomeRepos.findAll();
        List<Expense> ExpenseArea = expenseRepos.findAll();

        OptionalInt maxIncome = IncomeArea.stream().mapToInt(Income::getAmount).max();
        OptionalInt maxExpense = ExpenseArea.stream().mapToInt(Expense::getAmount).max();
        OptionalInt minIncome = IncomeArea.stream().mapToInt(Income::getAmount).min();
        OptionalInt minExpense = ExpenseArea.stream().mapToInt(Expense::getAmount).min();

        statsDto.setMaxIncome(maxIncome.orElse(0));
        statsDto.setMinIncome(minIncome.orElse(0));
        statsDto.setMaxExpense(maxExpense.orElse(0));
        statsDto.setMinExpense(minExpense.orElse(0));

        statsDto.setBalance(statsDto.getTotalIncome() - statsDto.getTotalExpense());    
        return statsDto;
    }
}
