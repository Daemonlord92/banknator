package com.example.banknator.transactions;

import com.example.banknator.shared.MessageResponse;
import com.example.banknator.transactions.dto.PostNewTransaction;
import com.example.banknator.transactions.dto.TransactionInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/apiv1/transaction")
public class TransactionController {
    protected final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> postNewTransaction(@RequestBody PostNewTransaction request) {
        try {
            return ResponseEntity.ok(transactionService.createTransaction(request));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionInformation>> getTransactionsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByAccountId(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
