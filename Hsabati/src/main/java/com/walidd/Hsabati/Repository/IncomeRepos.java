package com.walidd.Hsabati.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.walidd.Hsabati.Entity.Income;

public interface IncomeRepos extends JpaRepository<Income,Long>{
    List<Income> findByDateBetween(LocalDate start, LocalDate end);


    @Query("SELECT SUM(i.amount) FROM Income i")
    Integer getSumIncome();

    Optional<Income> findFirstByOrderByDateDesc();

}
