package rs.ac.singidunum.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableEurekaClient
@SpringBootApplication(
    scanBasePackages = {
        "rs.ac.singidunum.notification",
        "rs.ac.singidunum.amqp"
    }
)
@PropertySources({
    @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}

