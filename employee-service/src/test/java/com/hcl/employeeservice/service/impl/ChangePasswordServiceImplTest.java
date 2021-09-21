package com.hcl.employeeservice.service.impl;

import com.hcl.employeeservice.domain.Employee;
import com.hcl.employeeservice.repository.EmployeeRepository;
import com.hcl.employeeservice.service.ChangePasswordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ChangePasswordServiceImplTest {

    @TestConfiguration
    static class ChangePasswordServiceImplTestContextConfiguration {

        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public ChangePasswordService changePasswordService() {
            return new ChangePasswordServiceImpl();
        }
    }

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private ChangePasswordService changePasswordService;

    @Test
    public void testValidPassword() {
        Employee employee = new Employee();
        Mockito.doReturn(Optional.of(employee)).when(employeeRepository).findById(Mockito.anyLong());
        changePasswordService.changePassword(1l, "MichalWos!");

        Assertions.assertEquals("4f81ccbaa38bf26a2d822e25b78b2cba10bee1342767e854acd5e8d6e47a5bbe", employee.getPassword());
        Assertions.assertNotNull(employee.getPasswordExpired());
    }

    @Test
    public void testInValidPassword() {
        Employee employee = new Employee();
        Mockito.doReturn(Optional.of(employee)).when(employeeRepository).findById(Mockito.anyLong());
        ConstraintViolationException ex = Assertions.assertThrows(ConstraintViolationException.class,
                () -> changePasswordService.changePassword(1l, ""));
        Assertions.assertEquals(1, ex.getConstraintViolations().size());
        Assertions.assertEquals(
                "Password must be 8 or more characters in length.,Password must contain 1 or more uppercase characters.,Password must contain 1 or more lowercase characters.,Password must contain 1 or more special characters."
        , ex.getConstraintViolations().stream().findFirst().get().getMessage());
    }

    @Test
    public void testNullPassword() {
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> changePasswordService.changePassword(1l, null));
    }

    //TODO: test every rule separately
}
