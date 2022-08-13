package rs.ac.singidunum.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.clients.notification.NotificationRequest;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/notification")
public record NotificationController(NotificationService notificationService) {

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notification) {
        log.info("New notification {}", notification);
        notificationService.sendNotification(notification);
        // TODO: 8/13/2022 ResponseEntity for failed request
    }

}
