package ifpb.pod.sender;

import ifpb.pod.sender.repositories.MessageRepository;
import ifpb.pod.sender.repositories.MessageResultRepository;
import ifpb.pod.sender.repositories.SendedMessageRepository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class MainSender {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        //log
        System.out.println("Inicializado o serviço de Sender");
        //inicializar o repositorio
        MessageRepository repository = new MessageRepository();
        SendedMessageRepository sendedMessageRepository = new SendedMessageRepository();
        MessageResultRepository resultRepository = new MessageResultRepository();
        //inicializar o gerenciador de tarefas
        TaskManager.runTask(repository, sendedMessageRepository);
        //registre um observer
        ObserverImpl.registry(resultRepository);
        //inicializar o serviço para client app
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Sender", new SenderImpl(repository, resultRepository));
    }
}
