package rs.ac.singidunum.utils.email;

import rs.ac.singidunum.notification.Notification;

public interface EmailSender {
    public void send(Notification notification, String subject);
}
