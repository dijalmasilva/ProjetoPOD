package ifpb.pod.chatclient;

import ifpb.edu.br.pod.Channel;
import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.IServer;
import ifpb.pod.repositories.ClientNotifiedRepository;
import ifpb.pod.repositories.MessageRepository;
import ifpb.pod.repositories.NotificationRepository;
import ifpb.pod.repositories.SendedMessageRepository;
import ifpb.pod.sender.SenderTask;
import ifpb.pod.sender.ShowNotificationTask;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Timer;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class TaskManager {

    private IServer server;

    public TaskManager(MessageRepository messageRepository, SendedMessageRepository sendedMessageRepository,
                       NotificationRepository notificationRepository, ClientNotifiedRepository clientNotifiedRepository,
                       List<Client> clients) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        server = (IServer) registry.lookup("Server");
        Timer timer = new Timer();
        timer.schedule(new SenderTask(messageRepository, sendedMessageRepository), 2000, 2000);
        timer.schedule(new ShowNotificationTask(notificationRepository, clientNotifiedRepository, clients), 5000, 5000);
        timer.schedule(new SearchMessages(clientNotifiedRepository, clients), 3000, 3000);
    }

    public boolean registerClient(Client clientObserver) {

        try {
            if (clientObserver != null) {
                return server.addObserver(clientObserver);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean registerChannel(Client clientObserver, String nameChannel) {
        try {
            if (nameChannel != null) {
                return server.registerChannel(clientObserver, nameChannel);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Channel> listChannels() throws RemoteException {
        List<Channel> channels = server.listChannels();
        printChannels(channels);
        return channels;
    }

    public void registerInChannel(Client client, int id) throws RemoteException {
        server.registerInChannel(client, id);
    }

    private void printChannels(List<Channel> channels) {
        for (Channel c : channels) {
            System.out.println(c.getId() + " - " + c.getName());
            System.out.println("Qtde de usuários no grupo: " + c.getClientObservers().size());
        }
    }
}
