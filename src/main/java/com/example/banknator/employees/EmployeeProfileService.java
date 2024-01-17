package com.example.banknator.employees;

import com.example.banknator.employees.dto.PostUpdateProfile;
import com.example.banknator.entity.EmployeeProfile;
import com.example.banknator.shared.MessageResponse;

public interface EmployeeProfileService {
    MessageResponse saveEmployeeProfile(EmployeeProfile employeeProfile);
    MessageResponse updateEmployeeProfile(PostUpdateProfile request);
    MessageResponse terminateEmployee(Long id);
}
