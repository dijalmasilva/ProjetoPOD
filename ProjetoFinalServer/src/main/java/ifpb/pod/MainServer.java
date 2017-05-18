package ifpb.pod;

import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.IReceiver;
import ifpb.edu.br.pod.IServer;
import ifpb.pod.receiver.ReceiverImpl;
import ifpb.pod.repositories.NotificationRepository;
import ifpb.pod.server.ChannelRepository;
import ifpb.pod.server.ServerImpl;
import ifpb.pod.server.TaskManager;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class MainServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {

        List<Client> clients = new LinkedList<>();
        ChannelRepository channelRepository = new ChannelRepository();
        IServer server = new ServerImpl(clients, channelRepository);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", server);
        System.out.println("Servidor iniciado!");
        //
        IReceiver receiver = new ReceiverImpl();
        registry.bind("Receiver", receiver);
        System.out.println("Receiver iniciado!");

        NotificationRepository notificationRepository = new NotificationRepository(new LinkedList<>());

        TaskManager taskManager = new TaskManager(notificationRepository);
    }
}
