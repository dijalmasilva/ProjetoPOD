package ifpb.pod.server;

import ifpb.edu.br.pod.IAppData;
import ifpb.edu.br.pod.Notification;
import ifpb.pod.repositories.NotificationRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class NotificationTask extends TimerTask {

    private final NotificationRepository notificationRepository;

    public NotificationTask(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run() {

        try {
            Registry registry = LocateRegistry.getRegistry(1093);
            IAppData data = (IAppData) registry.lookup("AppData");
            List<Notification> notifications = data.listNotifications();

            if (notifications.size() > 0) {
                System.out.println("Adicionando notificações de AppData!");
                for (Notification n : notifications) {
                    notificationRepository.addNotification(n);
                }
            } else {
                System.out.println("Não há notificações novas de AppData");
            }

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
