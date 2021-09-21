package com.hcl.employeeservice.controller;

import com.hcl.employeeservice.service.ChangePasswordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChangePasswordController.class)
public class ChangePasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChangePasswordService changePasswordService;

    @Test
    public void testValidPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1/password").contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("MichalWos!")).andDo(print()).andExpect(status().isOk());
        Mockito.verify(changePasswordService, Mockito.times(1)).changePassword(1l, "MichalWos!");
    }

    @Test
    public void testInValidPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1/password").contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("michalwos")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json("[\"Password must contain 1 or more uppercase characters.,Password must contain 1 or more special characters.\"]"));
    }

    @Test
    public void testNoUser() throws Exception {
        Mockito.doThrow(NoSuchElementException.class).when(changePasswordService).changePassword(Mockito.anyLong(), Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/2/password").contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("MichalWos!")).andDo(print()).andExpect(status().isNotFound());
    }
}
