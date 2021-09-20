package com.hcl.employeeservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Employee entity
 */
@Entity
public class Employee {

    private String password;
    private Date passwordExpired;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(Date passwordExpired) {
        this.passwordExpired = passwordExpired;
    }
}
