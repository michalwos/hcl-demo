package com.hcl.employeeservice.handler;

import com.hcl.employeeservice.controller.ChangePasswordController;
import com.hcl.employeeservice.domain.Employee;
import com.hcl.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChangePasswordController.class)
public class ChangePasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    private static Employee employee;

    @Test
    public void testValidPassword() throws Exception {
        //TODO: validate in rest controller and mock ChangePasswordService
        Employee employee = new Employee();
        Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees/{1}/password").contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("MichalWos!")).andDo(print()).andExpect(status().isOk());
    }
}
