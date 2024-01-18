package com.example.banknator.bank;

import com.example.banknator.bank.dto.PostNewBank;
import com.example.banknator.bank.dto.PostUpdateBank;
import com.example.banknator.entity.Bank;
import com.example.banknator.shared.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    protected final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public MessageResponse createBank(PostNewBank postNewBank) {
        Bank bank = new Bank(
                postNewBank.name(),
                postNewBank.balance()
        );
        bankRepository.save(bank);
        return new MessageResponse("New Bank Created " + postNewBank.name());
    }

    @Override
    public MessageResponse updateBank(PostUpdateBank postUpdateBank) {
        Optional<Bank> bank = bankRepository.findById(postUpdateBank.id());
        if(bank.isEmpty()) throw new EntityNotFoundException("Bank not found");
        bank.get().setBankStatus(postUpdateBank.bankStatus());
        return new MessageResponse("Bank Updated");
    }

    @Override
    public MessageResponse deleteBank(Long id) {
        if(bankRepository.findById(id).isEmpty()) throw new EntityNotFoundException("Bank not found");
        return new MessageResponse("Bank Shut Down");
    }
}
