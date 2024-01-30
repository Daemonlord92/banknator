package com.example.banknator.applications;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.Enums.UserRole;
import com.example.banknator.applications.dto.PostNewHireApp;
import com.example.banknator.applications.dto.PostUpdateHireApp;
import com.example.banknator.bank.BankRepository;
import com.example.banknator.employees.EmployeeProfileService;
import com.example.banknator.entity.*;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.UserCredentialRepository;
import com.example.banknator.users.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HiringApplicationImpl implements HiringApplicationService{
    protected final Logger logger = LoggerFactory.getLogger(HiringApplicationImpl.class);
    protected final HiringApplicationRepository hiringApplicationRepository;
    protected final UserProfileRepository userProfileRepository;
    protected final BankRepository bankRepository;
    protected final EmployeeProfileService employeeProfileService;
    protected final UserCredentialRepository userCredentialRepository;

    public HiringApplicationImpl(HiringApplicationRepository hiringApplicationRepository, UserProfileRepository userProfileRepository, BankRepository bankRepository, EmployeeProfileService employeeProfileService, UserCredentialRepository userCredentialRepository) {
        this.hiringApplicationRepository = hiringApplicationRepository;
        this.userProfileRepository = userProfileRepository;
        this.bankRepository = bankRepository;
        this.employeeProfileService = employeeProfileService;
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public MessageResponse createHiringApp(PostNewHireApp request) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(request.userProfileId());
        if(userProfile.isEmpty()) throw new EntityNotFoundException("User Profile Not Found");
        HiringApplication application = new HiringApplication(
                request.position(),
                request.requestedSalary(),
                request.bankId(),
                userProfile.get()
        );
        hiringApplicationRepository.save(application);
        return new MessageResponse("Application Sent");
    }

    @Override
    public MessageResponse updateApplicationStatus(ApplicationStatus status, HiringApplication application) {
        if(status == ApplicationStatus.APPROVED){
            Optional<Bank> bank = bankRepository.findById(application.getBankId());
            if (bank.isEmpty()) throw new EntityNotFoundException("Bank Not Found");
            EmployeeProfile employeeProfile = new EmployeeProfile(
                application.getBankPosition(),
                    application.getRequestedSalary(),
                    application.getUserProfile(),
                    bank.get()
            );
            application.setApplicationStatus(status);
            logger.info(employeeProfileService.saveEmployeeProfile(employeeProfile).message());
            logger.info("HiringApplicationImpl:updateApplicationStatus:L65:Changing UserRole");
            UserCredential userCredential = application.getUserProfile().getUserCredential();
            userCredential.setRole(UserRole.ROLE_EMPLOYEE);
            logger.info("HiringApplicationImpl:updateApplicationStatus:L70:Saving Changes");
            userCredentialRepository.save(userCredential);
            hiringApplicationRepository.save(application);
            return new MessageResponse("Application Approved");
        } else if (status == ApplicationStatus.DECLINED){
            application.setApplicationStatus(status);
            hiringApplicationRepository.save(application);
            return new MessageResponse("Application Declined");
        } else {
            application.setApplicationStatus(status);
            hiringApplicationRepository.save(application);
            return new MessageResponse("Application Modified");
        }
    }

    @Override
    public MessageResponse updateApplication(PostUpdateHireApp request) {
        Optional<HiringApplication> application = hiringApplicationRepository.findById(request.id());
        if(application.isEmpty()) throw new EntityNotFoundException("Application doesn't Exists");
        application.get().setRequestedSalary(request.salary());
        application.get().setBankPosition(request.bankPosition());
        return updateApplicationStatus(ApplicationStatus.MODIFIED, application.get());
    }

    @Override
    public MessageResponse withdrawApplication(HiringApplication application) {
        return updateApplicationStatus(ApplicationStatus.DECLINED, application);
    }

    @Override
    public List<HiringApplication> getAllApps() {
        return hiringApplicationRepository.findAll();
    }

    @Override
    public List<HiringApplication> getAppsByUserProfileId(Long id) {
        return hiringApplicationRepository.findAllByUserProfileId(id);
    }
}
