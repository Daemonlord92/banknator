package com.example.banknator.bank;

import com.example.banknator.bank.dto.PostNewBank;
import com.example.banknator.bank.dto.PostUpdateBank;
import com.example.banknator.entity.Bank;
import com.example.banknator.shared.MessageResponse;

import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    MessageResponse createBank(PostNewBank postNewBank);
    MessageResponse updateBank(PostUpdateBank postUpdateBank);
    MessageResponse deleteBank(Long id);

}
