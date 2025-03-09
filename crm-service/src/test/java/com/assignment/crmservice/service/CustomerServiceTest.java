package com.assignment.crmservice.service;


import com.assignment.crmservice.entity.Customer;
import com.assignment.crmservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setFirstname("Firstname");
        customer.setLastname("Lastname");
        customer.setCustomerDate(LocalDate.parse("2025-03-09"));
        customer.setIsVIP(true);
        customer.setStatusCode("ACTIVE");
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer customer = customerService.getCustomerById(1L);
        assertNotNull(customer);
        assertEquals("Firstname", customer.getFirstname());
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);
        assertNotNull(savedCustomer);
        assertEquals("Firstname", savedCustomer.getFirstname());
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.updateCustomer(1L, customer);
        assertNotNull(savedCustomer);
        assertEquals("Firstname", savedCustomer.getFirstname());
    }

    @Test
    void testGetCustomerAll() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull(customers);
        assertEquals(customers.size(), 1);
    }
}

