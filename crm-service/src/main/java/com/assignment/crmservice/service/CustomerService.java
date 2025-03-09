package com.assignment.crmservice.service;

import com.assignment.crmservice.entity.Customer;
import com.assignment.crmservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        customer.setCreatedOn(LocalDateTime.now());
        customer.setModifiedOn(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer existingCustomer = getCustomerById(customerId);
        if (existingCustomer != null) {
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setCustomerDate(customer.getCustomerDate());
            existingCustomer.setIsVIP(customer.getIsVIP());
            existingCustomer.setStatusCode(customer.getStatusCode());
            existingCustomer.setModifiedOn(LocalDateTime.now());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }
}

