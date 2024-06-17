package com.example.banknator.bank;

import com.example.banknator.bank.dto.PostNewBank;
import com.example.banknator.bank.dto.PostUpdateBank;
import com.example.banknator.entity.Bank;
import com.example.banknator.shared.MessageResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv1/bank")
public class BankController {

    protected final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bank>> getAllBanks() {
        return new ResponseEntity<>(bankService.getAllBanks(), HttpStatus.FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> postNewBank(@RequestBody PostNewBank postNewBank) {
        return new ResponseEntity<>(bankService.createBank(postNewBank), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> postUpdateBank(PostUpdateBank postUpdateBank) {
        return new ResponseEntity<>(bankService.updateBank(postUpdateBank), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteBank(@PathVariable Long id) {
        return new ResponseEntity<>(bankService.deleteBank(id), HttpStatus.ACCEPTED);
    }
}
