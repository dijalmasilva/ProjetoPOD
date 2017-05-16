package ifpb.pod.receiver;

import ifpb.edu.br.pod.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class Receiver extends UnicastRemoteObject implements IReceiver, Subject {

    private final ResponseMessageRepository repository;

    private Observer observer = null;

    protected Receiver(ResponseMessageRepository repository) throws RemoteException {
        this.repository = repository;
    }

    @Override
    public void delivery(Message msg) throws RemoteException {

        System.out.println("Recebendo uma mensagem e tentando encaminhar para o server...");

        Registry registry = LocateRegistry.getRegistry(1099);

        try {
            IServer server = (IServer) registry.lookup("Server");
            MessageResult result = server.processMessageReceive(msg);
            repository.add(result);

            observer.notify(result);
        } catch (NotBoundException e) {
            throw new RuntimeException("Não deu certo!");
        }

    }

    @Override
    public MessageResult result(String id) throws RemoteException {
        return repository.get(id);
    }

    @Override
    public void registry(Observer observer) throws RemoteException {
        this.observer = observer;
    }
}
