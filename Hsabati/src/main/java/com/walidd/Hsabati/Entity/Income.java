package com.walidd.Hsabati.Entity;



import com.walidd.Hsabati.DTO.IncomeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Income {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;
    private Integer amount;

    private String description;

    private LocalDate date;


    public IncomeDTO getIncomeDTO(){
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(id);
        incomeDTO.setAmount(amount);
        incomeDTO.setTitle(title);
        incomeDTO.setCategory(category);
        incomeDTO.setDescription(description);
        incomeDTO.setDate(date);
        return incomeDTO;
    }
}
