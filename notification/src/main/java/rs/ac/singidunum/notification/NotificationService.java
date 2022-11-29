package rs.ac.singidunum.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.clients.notification.NotificationRequest;
import rs.ac.singidunum.notification.config.NotificationConfig;
import rs.ac.singidunum.notification.email.EmailSender;

@Slf4j
@Service
public record NotificationService(EmailSender emailSender,
                                  NotificationRepository notificationRepository,
                                  NotificationConfig notificationConfig) {

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender(notificationConfig.getSender())
                .build();

        try {
            notificationRepository.save(notification);
            // TODO: fix credentials for email account
            // emailSender.send(notification, notificationConfig.getSubject());
        } catch (Exception e) {
            log.error("Failure for notification execution {}", e.toString());
        }
    }
}
