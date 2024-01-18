package com.example.banknator.applications;


import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.applications.dto.PostNewLoanApp;
import com.example.banknator.entity.LoanApplication;
import com.example.banknator.shared.MessageResponse;

import java.util.List;

public interface LoanApplicationService {
    MessageResponse createLoanApp(PostNewLoanApp postNewLoanApp);
    MessageResponse updateLoanStatus(Long loanAppId, ApplicationStatus status);
    List<LoanApplication> getAllLoanApps();
}
