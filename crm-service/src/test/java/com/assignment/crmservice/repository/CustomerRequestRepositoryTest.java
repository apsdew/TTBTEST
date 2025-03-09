package com.assignment.crmservice.repository;

import com.assignment.crmservice.constant.RequestStatus;
import com.assignment.crmservice.entity.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRequestRepositoryTest {

    @Autowired
    private CustomerRequestRepository customerRequestRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testSaveAndFindById() {
        CustomerRequest request = new CustomerRequest();
        request.setCustomerId("00001");
        request.setIssueDescription("Can't open browser");
        request.setStatus(RequestStatus.IN_PROGRESS);

        CustomerRequest savedRequest = testEntityManager.persistFlushFind(request);

        Optional<CustomerRequest> foundRequest = customerRequestRepository.findById(savedRequest.getId());

        assertThat(foundRequest).isPresent();
        assertThat(foundRequest.get().getCustomerId()).isEqualTo("00001");
        assertThat(foundRequest.get().getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }
}
