package com.example.banknator.accounts;

import com.example.banknator.accounts.dto.AccountInformation;
import com.example.banknator.accounts.dto.PostNewAccountInformation;
import com.example.banknator.shared.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/apiv1/account")
public class AccountController {
    protected final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createNewAccount")
    public ResponseEntity<MessageResponse> postNewAccountInformation(@RequestBody PostNewAccountInformation request){
        try{
            return ResponseEntity.ok(accountService.createAccount(request));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @GetMapping("/getAccountInformationByUser")
    public ResponseEntity<List<AccountInformation>> getAccountInfoByUserProfileId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.getAllAccountsByUserProfileId(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @GetMapping("/getAccountInformationById")
    public ResponseEntity<AccountInformation> getAccountById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.getAccountById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/disableAccount")
    public ResponseEntity<MessageResponse> disableAccount(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.disableAccount(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/enableAccount")
    public ResponseEntity<MessageResponse> enableAccount(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.enableAccount(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }
}
