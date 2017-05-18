package ifpb.pod.sender;

import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.Notification;
import ifpb.pod.repositories.ClientNotifiedRepository;
import ifpb.pod.repositories.NotificationRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class ShowNotificationTask extends TimerTask {

    private final NotificationRepository notificationRepository;
    private final ClientNotifiedRepository clientNotifiedRepository;
    private final List<Client> clients;

    public ShowNotificationTask(NotificationRepository notificationRepository, ClientNotifiedRepository clientNotifiedRepository, List<Client> clients) {
        this.notificationRepository = notificationRepository;
        this.clientNotifiedRepository = clientNotifiedRepository;
        this.clients = clients;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {

        List<Notification> notifications = notificationRepository.getNotifications();

        if (notifications.size() > 0) {
            System.out.println("Notificando clientes!");

            for (Notification notification : notifications) {

                if (isConnected(notification.getClient())) {
                    notification.getClient().showNotify("TOKEN NOTIFICAÇÃO: " + notification.getToken());
                    notification.getClient().showNotify("Nova mensagem do grupo: " + notification.getChannel().getName());
                    notification.getClient().showNotify("Data: " + notification.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    System.out.println();
                    System.out.println("Cliente logado e notificado!");
                    clientNotifiedRepository.addToken(notification.getToken());
                    System.out.println("Guardando token para buscar posteriormente a mensagem...");
                    notificationRepository.removeNotification(notification);
                } else {
                    System.out.println("Cliente não logado!");
                }
            }
        }
    }

    private boolean isConnected(Client client) {

        for (Client c : clients) {
            if (c.getEmail().equals(client.getEmail())) {
                return c.isConnected();
            }
        }

        return false;
    }
}
