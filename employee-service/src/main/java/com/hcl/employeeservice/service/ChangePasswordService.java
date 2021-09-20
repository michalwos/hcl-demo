package com.hcl.employeeservice.service;

import com.google.common.hash.Hashing;
import com.hcl.employeeservice.domain.Employee;
import com.hcl.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Change password service
 */
@Service
public class ChangePasswordService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Changes password of given employee
     *
     * @param employeeId employee id
     * @param newPassword new password
     */
    public void changePassword(Long employeeId, @Valid  String newPassword) {
       String hashedPassword = Hashing.sha256()
                .hashString(newPassword, StandardCharsets.UTF_8)
                .toString();
       Employee employee = employeeRepository.findById(employeeId).get();
       employee.setPassword(hashedPassword);
       employee.setPasswordExpired(new Date());
       employeeRepository.save(employee);
    }
}
