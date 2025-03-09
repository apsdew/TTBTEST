package com.assignment.crmservice.controller;


import com.assignment.crmservice.constant.RequestStatus;
import com.assignment.crmservice.entity.CustomerRequest;
import com.assignment.crmservice.service.CustomerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRequestController {

    @Autowired
    private CustomerRequestService customerRequestService;

    @PostMapping("/request")
    public ResponseEntity<CustomerRequest> createRequest(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerRequestService.createRequest(request));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<CustomerRequest>> getAllRequests() {
        return ResponseEntity.ok(customerRequestService.getAllRequests());
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<CustomerRequest> getRequestById(@PathVariable Long id) {
        return customerRequestService.getRequestById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/request/{id}/status")
    public ResponseEntity<CustomerRequest> updateRequestStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        return ResponseEntity.ok(customerRequestService.updateRequestStatus(id, status));
    }
}

