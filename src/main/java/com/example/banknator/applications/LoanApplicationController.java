package com.example.banknator.applications;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.applications.dto.PostNewLoanApp;
import com.example.banknator.applications.dto.PostUpdateLoanApp;
import com.example.banknator.entity.LoanApplication;
import com.example.banknator.shared.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv1/loanApp")
public class LoanApplicationController {

    protected final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanApplication>> getAllLoanApp(){
        return new ResponseEntity<>(loanApplicationService.getAllLoanApps(), HttpStatus.FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> postNewLoanApp(@RequestBody PostNewLoanApp postNewLoanApp) {
        return new ResponseEntity<>(loanApplicationService.createLoanApp(postNewLoanApp), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> postUpdateLoanStatus(@RequestBody PostUpdateLoanApp postUpdateLoanApp) {
        return  new ResponseEntity<>(loanApplicationService.updateLoanStatus(postUpdateLoanApp.id(), postUpdateLoanApp.status()), HttpStatus.ACCEPTED);
    }
}
