package ifpb.pod.appdata;

import ifpb.edu.br.pod.*;
import ifpb.pod.interfaces.TxOperation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class AppDataImpl extends UnicastRemoteObject implements IAppData, TxOperation {

    private List<Notification> notifications;
    private List<Message> messages;
    private List<Notification> notificationsTransactional;
    private List<Message> messagesTransactional;
    private boolean inTransaction;

    public AppDataImpl(List<Notification> notifications, List<Message> messages) throws RemoteException {
        this.notifications = notifications;
        this.messages = messages;
        inTransaction = false;
        this.notificationsTransactional = null;
        this.messagesTransactional = null;
    }

    @Override
    public void prepare() throws RemoteException {
        inTransaction = true;
        this.notificationsTransactional = notifications;
        this.messagesTransactional = messages;
    }

    @Override
    public void commit() throws RemoteException {

        if (inTransaction) {
            this.notifications = notificationsTransactional;
            this.messages = messagesTransactional;
            inTransaction = false;
            System.out.println("Commitou alterações em AppData!");
        } else {
            throw new RemoteException("Rollback service");
        }
    }

    @Override
    public void rollback() throws RemoteException {

        if (inTransaction) {
            inTransaction = false;
            notificationsTransactional = null;
            messagesTransactional = null;
        } else {
            throw new RemoteException("Rollback not supported.");
        }

    }

    @Override
    public void receiveMessageAndCreateNotification(MessageSended messageSended) throws RemoteException {

        prepare();

        if (inTransaction) {
            try {
                List<Client> clients = filterClients(messageSended.getClient(), messageSended.getChannel().getClientObservers());
                generateNotificationsAndMessages(messageSended.getMessage(), messageSended.getChannel(), clients);
                commit();
            } catch (Exception e) {
                rollback();
            }
        } else {
            throw new RemoteException("Não foi possível gerar notificações!");
        }
    }

    @Override
    public List<Notification> listNotifications() throws RemoteException {
        return notifications;
    }

    @Override
    public Message getMessage(String idMessage) throws RemoteException {
        Message messageResult = null;
        for (Message m : messages) {
            System.out.println("ID armazenada: " + m.getId());
            System.out.println("ID recebida: " + idMessage);
            if (Objects.equals(m.getId(), idMessage)) {
                System.out.println("Mensagem identificada!");
                messageResult = m;
            }
        }
        if (messageResult != null) {
            messages.remove(messageResult);
            System.out.println("Mensagem removida do AppData e encaminhada");
        } else {
            System.out.println("Não foi encontrada a mensagem!");
        }

        return messageResult;
    }

    @Override
    public void removeNotification(Notification notification) throws RemoteException {

        prepare();

        if (inTransaction) {

            try {
                deleteNotification(notification);
            } catch (Exception e) {
                rollback();
            }

            commit();
        } else {
            throw new RemoteException("Não foi possível remover a notificação");
        }
    }

    private void generateNotificationsAndMessages(Message message, Channel channel, List<Client> clients) {
        String tokenDefault = message.getId();
        if (clients.size() > 0) {
            for (Client c : clients) {
                Notification notification = createNotification(channel, c, message);
                this.notificationsTransactional.add(notification);
                saveMessage(message, c);
            }
        }
    }

    private void saveMessage(Message message, Client client) {
        Message m = new Message(message.getText());
        String newID = message.getId() + ":" + client.getEmail();
        m.setId(newID);
        this.messagesTransactional.add(m);
    }

    private Notification createNotification(Channel channel, Client client, Message message) {
        Notification notification = new Notification(channel, client, LocalDateTime.now(), message.getText());
        notification.setToken(message.getId() + ":" + client.getEmail());
        System.out.println("Gerando notificação " + notification.getToken() + " para o cliente:" + client.getEmail());
        return notification;
    }

    private List<Client> filterClients(Client client, List<Client> clients) {
        List<Client> result = new LinkedList<>();
        for (Client c : clients) {
            if (!Objects.equals(c.getEmail(), client.getEmail())) {
                result.add(c);
            }
        }

        return result;
    }

    private void deleteNotification(Notification notification) {

        for (int i = 0; i < notificationsTransactional.size(); i++) {

            if (Objects.equals(notification.getToken(), notificationsTransactional.get(i).getToken())) {
                System.out.println("AppData deletou a notificação!");
                notificationsTransactional.remove(i);
                break;
            }
        }
    }
}
