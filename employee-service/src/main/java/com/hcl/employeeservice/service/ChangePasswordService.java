package com.hcl.employeeservice.service;

import com.hcl.employeeservice.validator.ValidPassword;

/**
 * Change password service interface
 */
public interface ChangePasswordService {

    /**
     * Changes password of given employee
     *
     * @param employeeId employee id
     * @param newPassword new password
     */
     void changePassword(Long employeeId, @ValidPassword String newPassword);
}
