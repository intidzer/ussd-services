package rs.ac.singidunum.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "notification",
        path = "api/v1/notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping
    void sendNotification(@RequestBody NotificationRequest notification);

}
