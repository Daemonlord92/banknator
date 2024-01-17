package com.example.banknator.applications;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.applications.dto.PostNewHireApp;
import com.example.banknator.applications.dto.PostUpdateHireApp;
import com.example.banknator.entity.HiringApplication;
import com.example.banknator.shared.MessageResponse;

import java.util.List;

public interface HiringApplicationService {
    MessageResponse createHiringApp(PostNewHireApp request);
    MessageResponse updateApplicationStatus(ApplicationStatus status, HiringApplication application);
    MessageResponse updateApplication(PostUpdateHireApp request);
    MessageResponse withdrawApplication(HiringApplication application);
    List<HiringApplication> getAllApps();
}
