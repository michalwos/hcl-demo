package com.hcl.employeeservice.controller;

import com.hcl.employeeservice.service.ChangePasswordService;
import com.hcl.employeeservice.validator.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Change password rest api controller
 */
@RestController
@RequestMapping("/employees")//better to use /api/v1/employees
@Validated
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    /**
     * Change password endpoint
     *
     * @param newPassword new password
     */
    @PutMapping(path = "{id}/password", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void changePassword(@PathVariable Long id, @ValidPassword @RequestBody String newPassword) {
        changePasswordService.changePassword(id, newPassword);
    }
}
