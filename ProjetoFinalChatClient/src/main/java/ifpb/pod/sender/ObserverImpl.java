package ifpb.pod.sender;

import ifpb.edu.br.pod.MessageResult;
import ifpb.edu.br.pod.Observer;
import ifpb.edu.br.pod.Subject;
import ifpb.pod.sender.repositories.MessageResultRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class ObserverImpl extends UnicastRemoteObject implements Observer {

    private final MessageResultRepository resultRepository;

    protected ObserverImpl(MessageResultRepository resultRepository) throws RemoteException {
        this.resultRepository = resultRepository;
    }

    @Override
    public void notify(MessageResult result) throws RemoteException {
        resultRepository.add(result);
    }

    public static void registry(MessageResultRepository rep) {
        boolean registered = false;

        while (!registered) {
            try {
                Registry registry = LocateRegistry.getRegistry(1091);
                Subject subject = (Subject) registry.lookup("Subject");
                subject.registry(new ObserverImpl(rep));
                registered = true;
            } catch (RemoteException | NotBoundException e) {
                continue;
            }
        }
    }
}
