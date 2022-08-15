package rs.ac.singidunum.customer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notification.registration")
public class CustomerServiceConfigurationProperties {

    private String message;

}