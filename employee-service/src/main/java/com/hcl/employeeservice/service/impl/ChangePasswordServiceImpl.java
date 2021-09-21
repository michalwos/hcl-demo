package com.hcl.employeeservice.service.impl;

import com.google.common.hash.Hashing;
import com.hcl.employeeservice.domain.Employee;
import com.hcl.employeeservice.repository.EmployeeRepository;
import com.hcl.employeeservice.service.ChangePasswordService;
import com.hcl.employeeservice.validator.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Change password service implementation
 */
@Service
@Validated
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void changePassword(Long employeeId, @ValidPassword String newPassword) {
        String hashedPassword = Hashing.sha256()
                .hashString(newPassword, StandardCharsets.UTF_8)
                .toString();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setPassword(hashedPassword);
        employee.setPasswordExpired(new Date());
        employeeRepository.save(employee);
    }
}
