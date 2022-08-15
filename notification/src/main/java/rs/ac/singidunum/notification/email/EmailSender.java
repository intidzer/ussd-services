package rs.ac.singidunum.notification.email;

import rs.ac.singidunum.notification.Notification;

public interface EmailSender {
    public void send(Notification notification, String subject);
}
