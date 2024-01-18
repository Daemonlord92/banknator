package com.example.banknator.employees;

import com.example.banknator.employees.dto.PostUpdateProfile;
import com.example.banknator.shared.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiv1/employee")
public class EmployeeProfileController {
    protected final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> postUpdate(@RequestBody PostUpdateProfile request) {
        return new ResponseEntity<>(employeeProfileService.updateEmployeeProfile(request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/terminate/{id}")
    public ResponseEntity<MessageResponse> postTerminate(@PathVariable Long id) {
        return new ResponseEntity<>(employeeProfileService.terminateEmployee(id), HttpStatus.OK);
    }
}
