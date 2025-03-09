package com.assignment.crmservice.service;


import com.assignment.crmservice.constant.RequestStatus;
import com.assignment.crmservice.entity.CustomerRequest;
import com.assignment.crmservice.repository.CustomerRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerRequestService {

    @Autowired
    private CustomerRequestRepository customerRequestRepository;

    @Transactional
    public CustomerRequest createRequest(CustomerRequest request) {
        request.setStatus(RequestStatus.IN_PROGRESS);
        return customerRequestRepository.save(request);
    }

    public List<CustomerRequest> getAllRequests() {
        return customerRequestRepository.findAll();
    }

    public Optional<CustomerRequest> getRequestById(Long id) {
        return customerRequestRepository.findById(id);
    }

    @Transactional
    public CustomerRequest updateRequestStatus(Long id, RequestStatus status) {
        CustomerRequest request = customerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return customerRequestRepository.save(request);
    }
}

