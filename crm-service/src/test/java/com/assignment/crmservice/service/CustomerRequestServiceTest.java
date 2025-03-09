package com.assignment.crmservice.service;

import com.assignment.crmservice.constant.RequestStatus;
import com.assignment.crmservice.entity.CustomerRequest;
import com.assignment.crmservice.repository.CustomerRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerRequestServiceTest {

    @Mock
    private CustomerRequestRepository customerRequestRepository;

    @InjectMocks
    private CustomerRequestService customerRequestService;

    private CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setCustomerId("00001");
        customerRequest.setIssueDescription("Can't open browser");
        customerRequest.setStatus(RequestStatus.IN_PROGRESS);
    }

    @Test
    void testCreateRequest_Success() {
        when(customerRequestRepository.save(any(CustomerRequest.class))).thenReturn(customerRequest);

        CustomerRequest updatedRequest = customerRequestService.createRequest(customerRequest);

        assertThat(updatedRequest.getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }

    @Test
    void testGetAllRequest_Success() {
        when(customerRequestRepository.findAll()).thenReturn(List.of(customerRequest));

        List<CustomerRequest> updatedRequests = customerRequestService.getAllRequests();

        assertThat(updatedRequests.size()).isEqualTo(1);
    }

    @Test
    void testGetAllRequest_NotFound() {
        when(customerRequestRepository.findAll()).thenReturn(List.of());

        List<CustomerRequest> updatedRequests = customerRequestService.getAllRequests();

        assertThat(updatedRequests.size()).isEqualTo(0);
    }

    @Test
    void testGetRequestById_Success() {
        when(customerRequestRepository.findById(any())).thenReturn(Optional.of((customerRequest)));

        Optional<CustomerRequest> updatedRequest = customerRequestService.getRequestById(1L);

        assertThat(updatedRequest).isEqualTo(Optional.of((customerRequest)));
    }

    @Test
    void testGetRequestById_NotFound() {
        Optional<CustomerRequest> optCustomerRequest = Optional.of(new CustomerRequest());
        when(customerRequestRepository.findById(any())).thenReturn(optCustomerRequest);

        Optional<CustomerRequest> updatedRequest = customerRequestService.getRequestById(1L);

        assertThat(updatedRequest).isEqualTo(optCustomerRequest);
    }

    @Test
    void testUpdateRequestStatus_Complete_Success() {
        when(customerRequestRepository.findById(1L)).thenReturn(Optional.of(customerRequest));
        when(customerRequestRepository.save(any(CustomerRequest.class))).thenReturn(customerRequest);

        CustomerRequest updatedRequest = customerRequestService.updateRequestStatus(1L, RequestStatus.COMPLETED);

        assertThat(updatedRequest.getStatus()).isEqualTo(RequestStatus.COMPLETED);
    }

    @Test
    void testUpdateRequestStatus_Canceled_Success() {
        when(customerRequestRepository.findById(2L)).thenReturn(Optional.of(customerRequest));
        when(customerRequestRepository.save(any(CustomerRequest.class))).thenReturn(customerRequest);

        CustomerRequest updatedRequest = customerRequestService.updateRequestStatus(2L, RequestStatus.CANCELED);

        assertThat(updatedRequest.getStatus()).isEqualTo(RequestStatus.CANCELED);
    }

    @Test
    void testUpdateRequestStatus_NotFound() {
        when(customerRequestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerRequestService.updateRequestStatus(1L, RequestStatus.COMPLETED));
    }
}
