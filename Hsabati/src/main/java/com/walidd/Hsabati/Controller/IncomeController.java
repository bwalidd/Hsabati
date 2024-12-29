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

import com.walidd.Hsabati.DTO.IncomeDTO;
import com.walidd.Hsabati.Entity.Income;

import com.walidd.Hsabati.Service.Income.IncomeSrvImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {
    
    private final IncomeSrvImpl incomeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(incomeService.getIncomeSrv(id));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // @DeleteMapping("/{id}")
    // public void Deletebyid(@PathVariable Long id)
    // {
        
    // }

    @PostMapping("")
    public ResponseEntity<?> createIncome(@RequestBody IncomeDTO dto)
    {
        Income saveIncome = incomeService.postIncome(dto);
        if (saveIncome != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(saveIncome);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> AllIncome(){
        return ResponseEntity.ok(incomeService.allProductSrv());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id,@RequestBody IncomeDTO dto){
        try{
            return ResponseEntity.ok(incomeService.updateIncome(id, dto));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void Deletebyid(@PathVariable Long id)
    {
        try{
            incomeService.DeletebyidSrv(id);
        }catch(EntityNotFoundException ex){
            ResponseEntity.ok(ex.getMessage());
        }catch(Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }

    }

}
