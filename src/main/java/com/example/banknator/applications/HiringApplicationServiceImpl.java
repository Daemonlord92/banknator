package com.example.banknator.applications;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.applications.dto.PostNewHireApp;
import com.example.banknator.applications.dto.PostUpdateHireApp;
import com.example.banknator.entity.HiringApplication;
import com.example.banknator.entity.UserProfile;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class HiringApplicationServiceImpl implements HiringApplicationService{
    protected final HiringApplicationRepository hiringApplicationRepository;
    protected final UserProfileRepository userProfileRepository;

    public HiringApplicationServiceImpl(HiringApplicationRepository hiringApplicationRepository, UserProfileRepository userProfileRepository) {
        this.hiringApplicationRepository = hiringApplicationRepository;
        this.userProfileRepository = userProfileRepository;
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
            application.setApplicationStatus(status);
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
}
