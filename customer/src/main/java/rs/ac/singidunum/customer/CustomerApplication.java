package rs.ac.singidunum.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
    scanBasePackages = {
        "rs.ac.singidunum.customer",
        "rs.ac.singidunum.amqp"
    }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "rs.ac.singidunum.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

