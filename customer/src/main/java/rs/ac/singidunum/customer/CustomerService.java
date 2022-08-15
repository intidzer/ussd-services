package rs.ac.singidunum.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.singidunum.clients.fraud.FraudClient;
import rs.ac.singidunum.clients.fraud.FraudsterResponse;
import rs.ac.singidunum.clients.notification.NotificationClient;
import rs.ac.singidunum.clients.notification.NotificationRequest;
import rs.ac.singidunum.customer.config.CustomerServiceConfigurationProperties;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              NotificationClient notificationClient,
                              CustomerServiceConfigurationProperties properties) {


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

        FraudsterResponse response = fraudClient.isFraudster(customer.getId());
        log.info("response: {}", response.isFraudster());

        if (response.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }

        // TODO: 8/10/2022 send notification

        log.info("Customer: {}", customer);

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                customer.getEmail(),
                String.format(properties.getMessage(), customer.getFirstName()));

        notificationClient.sendNotification(notificationRequest);
    }
}
