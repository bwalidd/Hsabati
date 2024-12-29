package com.walidd.Hsabati.Service.Income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.walidd.Hsabati.DTO.IncomeDTO;
import com.walidd.Hsabati.Entity.Income;
import com.walidd.Hsabati.Repository.IncomeRepos;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeSrvImpl implements IncomeService {
    
    private final IncomeRepos incomeRepos;

    // Get income by id
    public IncomeDTO getIncomeSrv(Long id) {
        Optional<Income> optionalIncome = incomeRepos.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDTO();
        } else {
            throw new EntityNotFoundException("No income found by id " + id);
        }
    }
    
    // Update income
    public Income updateIncome(Long id, IncomeDTO dto) {
        Optional<Income> optionalIncome = incomeRepos.findById(id);
        if (optionalIncome.isPresent()) {
            return saveORUpdateIncome(optionalIncome.get(), dto);
        } else {
            throw new EntityNotFoundException("No income found with id " + id);
        }
    }
    
    // Create income
    public Income postIncome(IncomeDTO dto) {
        return saveORUpdateIncome(new Income(), dto);
    }
    
    // Get all incomes
    public List<Income> allProductSrv() {
        return incomeRepos.findAll().stream()
        .sorted(Comparator.comparing(Income::getDate).reversed())
        .collect(Collectors.toList());
    }
    
    //delete income
    public void DeletebyidSrv(Long id)
    {
        Optional<Income> optionalIncome = incomeRepos.findById(id);
        if (optionalIncome != null)
        {
            incomeRepos.deleteById(id);
        }
        throw new EntityNotFoundException("no Income found with id " + id);
    }


    // Helper function
    private Income saveORUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setAmount(incomeDTO.getAmount());
        income.setId(incomeDTO.getId());
        income.setDescription(incomeDTO.getDescription());
        income.setCategory(incomeDTO.getCategory());
        income.setDate(incomeDTO.getDate());
        return incomeRepos.save(income);
    }

   
}
