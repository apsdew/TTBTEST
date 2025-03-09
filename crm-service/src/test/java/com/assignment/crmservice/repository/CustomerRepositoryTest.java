package com.assignment.crmservice.repository;

import com.assignment.crmservice.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testSaveAndFindById() {
        Customer customer = new Customer();
        customer.setFirstname("FirstName");
        customer.setLastname("Lastname");
        customer.setCustomerDate(LocalDate.parse("2025-03-09"));
        customer.setIsVIP(false);
        customer.setStatusCode("INACTIVE");

        Customer savedCustomer = testEntityManager.persistFlushFind(customer);
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getCustomerId());

        assertTrue(foundCustomer.isPresent());
        assertEquals("FirstName", foundCustomer.get().getFirstname());
    }
}

