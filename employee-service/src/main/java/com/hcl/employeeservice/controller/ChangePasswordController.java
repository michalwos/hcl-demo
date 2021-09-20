package com.hcl.employeeservice.controller;

import com.hcl.employeeservice.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Change password rest api controller
 */
@RestController
@RequestMapping("/employees")//better to use /api/v1/employees
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    /**
     * Change password endpoint
     *
     * @param newPassword new password
     */
    @PutMapping(path = "/{id}/password", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void changePassword(@PathVariable Long employeeId, String newPassword) {
        changePasswordService.changePassword(employeeId, newPassword);
    }
}
