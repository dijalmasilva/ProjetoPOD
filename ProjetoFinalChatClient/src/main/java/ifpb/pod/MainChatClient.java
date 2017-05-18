package ifpb.pod;

import ifpb.edu.br.pod.Channel;
import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.ISender;
import ifpb.edu.br.pod.Message;
import ifpb.pod.chatclient.TaskManager;
import ifpb.pod.repositories.ClientNotifiedRepository;
import ifpb.pod.repositories.MessageRepository;
import ifpb.pod.repositories.NotificationRepository;
import ifpb.pod.repositories.SendedMessageRepository;
import ifpb.pod.sender.SenderImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class MainChatClient {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException {

        Client clientObserver = new Client("Dijalma", "dijalmacz@gmail.com");
        Client clientObserver2 = new Client("Teste", "teste@gmail.com");
        clientObserver.login();


        MessageRepository messageRepository = new MessageRepository(new LinkedList<>());
        NotificationRepository notificationRepository = new NotificationRepository(new LinkedList<>());
        SendedMessageRepository sendedMessageRepository = new SendedMessageRepository(new LinkedList<>());
        ClientNotifiedRepository clientNotifiedRepository = new ClientNotifiedRepository(new LinkedList<>());
        List<Client> clients = new LinkedList<>();

        TaskManager taskManager = new TaskManager(messageRepository, sendedMessageRepository,
                notificationRepository, clientNotifiedRepository, clients);

        if (taskManager.registerClient(clientObserver)) {
            clients.add(clientObserver);
        }

        if (taskManager.registerClient(clientObserver2)) {
            clients.add(clientObserver2);
        }

        taskManager.registerChannel(clientObserver, "Grupo 1");
        taskManager.registerChannel(clientObserver, "Grupo 2");
        taskManager.registerChannel(clientObserver, "Grupo 3");
        taskManager.registerChannel(clientObserver, "Grupo 4");

        taskManager.registerInChannel(clientObserver2, 2);
        taskManager.registerInChannel(clientObserver2, 3);

        List<Channel> channels = taskManager.listChannels();

        ISender sender = new SenderImpl(messageRepository, notificationRepository);
        Registry registry = LocateRegistry.createRegistry(1091);
        registry.bind("Sender", sender);

        sender.sendMessage(clientObserver, channels.get(1), new Message("Lorem ipsum ciaoisjoa soaijdoaisjd!"));

        Thread.sleep(15000);
        Client client = clients.get(1);
        client.login();
        System.out.println("cliente 2 logou!");
    }
}
