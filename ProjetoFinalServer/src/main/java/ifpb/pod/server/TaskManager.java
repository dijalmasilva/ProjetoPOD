package ifpb.pod.server;

import ifpb.pod.repositories.NotificationRepository;

import java.util.Timer;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class TaskManager {

    public TaskManager(NotificationRepository notificationRepository) {
        Timer timer = new Timer();
        timer.schedule(new NotificationTask(notificationRepository), 3000, 5000);
        timer.schedule(new SendNotificationsTask(notificationRepository), 5000, 5500);
    }
}
