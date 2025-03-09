package com.assignment.crmservice.controller;

import com.assignment.crmservice.constant.RequestStatus;
import com.assignment.crmservice.entity.CustomerRequest;
import com.assignment.crmservice.service.CustomerRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerRequestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerRequestService customerRequestService;

    @InjectMocks
    private CustomerRequestController customerRequestController;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerRequestController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setCustomerId("00001");
        customerRequest.setIssueDescription("Can't open browser");
        customerRequest.setStatus(RequestStatus.IN_PROGRESS);
    }

    @Test
    void testCreateRequest_Success() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setCustomerId("00001");
        request.setIssueDescription("Can't open browser");
        when(customerRequestService.createRequest(any(CustomerRequest.class))).thenReturn(customerRequest);

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/customers/request")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRequestById_Success() throws Exception {
        when(customerRequestService.getRequestById(1L)).thenReturn(Optional.ofNullable(customerRequest));

        mockMvc.perform(get("/api/customers/request/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRequest_Success() throws Exception {
        when(customerRequestService.getAllRequests()).thenReturn(List.of(customerRequest));

        mockMvc.perform(get("/api/customers/requests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateRequestStatus_Success() throws Exception {
        when(customerRequestService.updateRequestStatus(1L, RequestStatus.COMPLETED)).thenReturn(customerRequest);

        mockMvc.perform(put("/api/customers/request/1/status")
                        .param("status", "COMPLETED")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
