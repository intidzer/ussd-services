package rs.ac.singidunum.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

        //todo: check if email is valid
        //todo: check if email is not taken
        // TODO: 8/11/2022 check phone number is valid
        customerRepository.save(customer);
        // TODO: 8/10/2022 check if is fraudster
        FraudsterResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudsterResponse.class,
                customer.getId()
        );

        if (response != null && response.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }

        // TODO: 8/10/2022 send notification

    }
}
