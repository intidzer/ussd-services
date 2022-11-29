package rs.ac.singidunum.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.amqp.RabbitMQMessageProducer;
import rs.ac.singidunum.clients.fraud.FraudClient;
import rs.ac.singidunum.clients.fraud.FraudCheckResponse;
import rs.ac.singidunum.clients.notification.NotificationRequest;
import rs.ac.singidunum.customer.configs.CustomerConfig;
import rs.ac.singidunum.customer.models.Customer;
import rs.ac.singidunum.customer.models.CustomerRegistrationRequest;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              CustomerConfig customerConfig,
                              RabbitMQMessageProducer producer) {


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

        //todo: check if email is valid
        //todo: check if email is not taken
        // TODO: 8/11/2022 check phone number is valid
        customerRepository.saveAndFlush(customer);
        // TODO: 8/10/2022 check if is fraudster

        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());
        log.info("response: {}", response.isFraudster());

        if (response.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }

        log.info("Customer: {}", customer);

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                customer.getEmail(),
                String.format(customerConfig.getMessage(), customer.getFirstName()));

            producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
                );
    }
}
