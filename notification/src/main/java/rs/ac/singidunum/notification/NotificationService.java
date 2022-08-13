package rs.ac.singidunum.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.clients.notification.NotificationRequest;
import rs.ac.singidunum.utils.email.EmailSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailSender emailSender;
    private final NotificationRepository notificationRepository;

    @Value("${notification.default.subject}")
    private String defaultSubject;
    @Value("${notification.default.sender}")
    private String defaultSender;

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                                .toCustomerId(notificationRequest.toCustomerId())
                                .toCustomerEmail(notificationRequest.toCustomerEmail())
                                .message(notificationRequest.message())
                                .sender(defaultSender)
                                .build();

        try {
            emailSender.send(notification, defaultSubject);
            notificationRepository.save(notification);
        } catch (Exception e) {
            log.error("Failure for notification execution {}", e.toString());
        }
    }
}
