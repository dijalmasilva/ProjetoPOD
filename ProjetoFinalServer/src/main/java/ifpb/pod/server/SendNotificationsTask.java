package ifpb.pod.server;

import ifpb.edu.br.pod.IAppData;
import ifpb.edu.br.pod.IReceiver;
import ifpb.edu.br.pod.ISender;
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
public class SendNotificationsTask extends TimerTask {

    private final NotificationRepository notificationRepository;

    public SendNotificationsTask(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {

            Registry registry = LocateRegistry.getRegistry(1091);
            ISender sender = (ISender) registry.lookup("Sender");

            List<Notification> notifications = notificationRepository.getNotifications();

            if (notifications.size() > 0) {
                System.out.println("Enviando notificações armazendas!");
                for (Notification n : notifications) {
                    boolean result = sender.getNotification(n);
                    if (result) {
                        notificationRepository.removeNotification(n);
                        System.out.println("Removendo notificação do SendNotificationTask");
                        Registry registry2 = LocateRegistry.getRegistry(1093);
                        IAppData appData = (IAppData) registry2.lookup("AppData");
                        appData.removeNotification(n);
                        System.out.println("Removeu notificação do AppData");
                    }
                }
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
