package ifpb.pod.sender;

import ifpb.edu.br.pod.*;
import ifpb.pod.repositories.MessageRepository;
import ifpb.pod.repositories.NotificationRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class SenderImpl extends UnicastRemoteObject implements ISender {

    private final MessageRepository messageRepository;
    private final NotificationRepository notificationRepository;

    public SenderImpl(MessageRepository messageRepository, NotificationRepository notificationRepository) throws RemoteException {
        this.messageRepository = messageRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendMessage(Client client, Channel channel, Message message) throws RemoteException {
        MessageSended messageSended = new MessageSended(client, channel, message);
        messageRepository.addMessage(messageSended);
        System.out.println("Mensagem recebida pelo Sender");
    }

    @Override
    public boolean getNotification(Notification notification) throws RemoteException {
        try {
            System.out.println("SenderImpl recebeu notificação: " + notification.getToken());
            this.notificationRepository.addNotification(notification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
