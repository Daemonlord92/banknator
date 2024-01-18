package com.example.banknator.applications;

import com.example.banknator.Enums.AccountType;
import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.accounts.AccountRepository;
import com.example.banknator.accounts.AccountService;
import com.example.banknator.accounts.dto.PostNewAccountInformation;
import com.example.banknator.applications.dto.PostNewLoanApp;
import com.example.banknator.entity.CreditAccount;
import com.example.banknator.entity.LoanApplication;
import com.example.banknator.entity.UserProfile;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationImpl implements LoanApplicationService{

    protected final LoanApplicationRepository loanApplicationRepository;
    protected final UserProfileRepository userProfileRepository;
    protected final AccountService accountService;

    public LoanApplicationImpl(LoanApplicationRepository loanApplicationRepository, UserProfileRepository userProfileRepository, AccountService accountService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userProfileRepository = userProfileRepository;
        this.accountService = accountService;
    }

    @Override
    public MessageResponse createLoanApp(PostNewLoanApp postNewLoanApp) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(postNewLoanApp.userProfileId());
        if(userProfile.isEmpty()) throw new EntityNotFoundException("User not found");
        LoanApplication loanApplication = new LoanApplication(
                postNewLoanApp.amount(),
                postNewLoanApp.interestRate(), 
                userProfile.get()
        );
        LoanApplication finalLoanApplication = loanApplicationRepository.save(loanApplication);
        Thread thread = new Thread(() -> {
            try{
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            
            if(userProfile.get().getCreditScore() < 580) {
                if(finalLoanApplication.getId() > 0){
                    finalLoanApplication.setApplicationStatus(ApplicationStatus.DECLINED);
                    loanApplicationRepository.save(finalLoanApplication);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return new MessageResponse("Loan App Posted");
    }

    @Override
    public MessageResponse updateLoanStatus(Long loanAppId, ApplicationStatus status) {
        Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(loanAppId);
        if(loanApplication.isEmpty()) throw new EntityNotFoundException("Loan App Not Found");
        if(status.equals(ApplicationStatus.APPROVED)){
            loanApplication.get().setApplicationStatus(status);
            PostNewAccountInformation postNewAccountInformation = new PostNewAccountInformation(
                    loanApplication.get().getUserProfile().getId(),
                    AccountType.LOAN,
                    loanApplication.get().getAmount()
            );
            accountService.createAccount(postNewAccountInformation);
            loanApplicationRepository.save(loanApplication.get());
        }
        if(status.equals(ApplicationStatus.DECLINED)) {
            loanApplication.get().setApplicationStatus(status);
            loanApplicationRepository.save(loanApplication.get());
        }
        return new MessageResponse("Loan Updated");
    }

    @Override
    public List<LoanApplication> getAllLoanApps() {
        return loanApplicationRepository.findAll();
    }
}
