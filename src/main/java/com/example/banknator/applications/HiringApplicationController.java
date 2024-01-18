package com.example.banknator.applications;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.applications.dto.PostNewHireApp;
import com.example.banknator.applications.dto.PostUpdateHireApp;
import com.example.banknator.entity.HiringApplication;
import com.example.banknator.shared.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv1/hireapp")
public class HiringApplicationController {
    protected final HiringApplicationService hiringApplicationService;

    public HiringApplicationController(HiringApplicationService hiringApplicationService) {
        this.hiringApplicationService = hiringApplicationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<HiringApplication>> getApp() {
        return new ResponseEntity<>(hiringApplicationService.getAllApps(), HttpStatus.FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> postNewApp(@RequestBody PostNewHireApp request) {
        return new ResponseEntity<>(hiringApplicationService.createHiringApp(request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<MessageResponse> withdrawRequest(HiringApplication application) {
        return new ResponseEntity<>(hiringApplicationService.withdrawApplication(application), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateApp")
    public ResponseEntity<MessageResponse> postUpdateApp(PostUpdateHireApp postUpdateHireApp) {
        return new ResponseEntity<>(hiringApplicationService.updateApplication(postUpdateHireApp), HttpStatus.ACCEPTED);
    }

    @PutMapping("updateAppStatus")
    public ResponseEntity<MessageResponse> postUpdateStatus(@RequestBody ApplicationStatus applicationStatus, HiringApplication application) {
        return new ResponseEntity<>(hiringApplicationService.updateApplicationStatus(applicationStatus, application), HttpStatus.ACCEPTED);
    }
}
