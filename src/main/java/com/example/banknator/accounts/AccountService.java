package com.example.banknator.accounts;

import com.example.banknator.accounts.dto.AccountInformation;
import com.example.banknator.accounts.dto.PostNewAccountInformation;
import com.example.banknator.entity.Transaction;
import com.example.banknator.shared.MessageResponse;

import java.util.List;

public interface AccountService {
    MessageResponse createAccount(PostNewAccountInformation request);
    List<AccountInformation> getAllAccountsByUserProfileId(long id);
    AccountInformation getAccountById(long id);
    MessageResponse updateBalance(Transaction transaction);
    MessageResponse disableAccount(Long id);
    MessageResponse enableAccount(Long id);
}
