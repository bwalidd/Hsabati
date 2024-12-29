package com.walidd.Hsabati.DTO;
import java.time.LocalDate;

import lombok.Data;

@Data
public class IncomeDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer amount;
    private LocalDate date;
}
