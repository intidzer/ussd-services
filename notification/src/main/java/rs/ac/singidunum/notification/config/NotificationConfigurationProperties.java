package rs.ac.singidunum.notification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notification.default")
public class NotificationConfigurationProperties {

    private String subject;
    private String sender;

}