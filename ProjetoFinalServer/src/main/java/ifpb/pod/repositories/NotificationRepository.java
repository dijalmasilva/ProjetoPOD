package ifpb.pod.repositories;

import ifpb.edu.br.pod.Notification;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class NotificationRepository {

    private List<Notification> notifications;

    public NotificationRepository(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(Notification notification) {
        boolean exist = false;
        for (Notification n : notifications) {
            if (notification.getToken().equals(n.getToken())) {
                exist = true;
            }
        }

        if (!exist) {
            this.notifications.add(notification);
        }
    }

    public void removeNotification(Notification notification) {
        this.notifications.remove(notification);
    }
}
