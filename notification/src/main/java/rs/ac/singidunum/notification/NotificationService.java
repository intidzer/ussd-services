package rs.ac.singidunum.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.clients.notification.NotificationRequest;
import rs.ac.singidunum.notification.config.NotificationConfigurationProperties;
import rs.ac.singidunum.notification.email.EmailSender;

@Slf4j
@Service
public record NotificationService(EmailSender emailSender,
                                  NotificationRepository notificationRepository,
                                  NotificationConfigurationProperties notificationProperties) {

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender(notificationProperties.getSender())
                .build();

        try {
            emailSender.send(notification, notificationProperties.getSubject());
            notificationRepository.save(notification);
        } catch (Exception e) {
            log.error("Failure for notification execution {}", e.toString());
        }
    }
}
