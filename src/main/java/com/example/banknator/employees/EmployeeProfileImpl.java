package com.example.banknator.employees;

import com.example.banknator.employees.dto.PostUpdateProfile;
import com.example.banknator.entity.EmployeeProfile;
import com.example.banknator.shared.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeProfileImpl implements EmployeeProfileService{
    protected final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeProfileImpl(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Override
    public MessageResponse saveEmployeeProfile(EmployeeProfile employeeProfile) {
        employeeProfileRepository.save(employeeProfile);
        return new MessageResponse("Employee saved to database");
    }

    @Override
    public MessageResponse updateEmployeeProfile(PostUpdateProfile request) {
        Optional<EmployeeProfile> employeeProfile = employeeProfileRepository.findById(request.id());
        if (employeeProfile.isEmpty()) throw new EntityNotFoundException("Employee not found");
        if(!employeeProfile.get().getPosition().equals(request.position())){
            employeeProfile.get().setPosition(request.position());
        }
        if(!employeeProfile.get().getSalary().equals(request.salary())) {
            employeeProfile.get().setSalary(request.salary());
        }
        employeeProfileRepository.save(employeeProfile.get());
        return new MessageResponse("Employee Updated");
    }

    @Override
    public MessageResponse terminateEmployee(Long id) {
        Optional<EmployeeProfile> employeeProfile = employeeProfileRepository.findById(id);
        if(employeeProfile.isEmpty()) throw new EntityNotFoundException("Employee not found");
        employeeProfileRepository.delete(employeeProfile.get());
        return new MessageResponse("Employee " + employeeProfile.get().getUserProfile().getFirstName() + " terminated!");
    }
}
