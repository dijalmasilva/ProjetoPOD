package ifpb.pod.receiver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class MainReceiver {

    public static void main(String[] args) throws AlreadyBoundException, RemoteException {

        //
        System.out.println("Inicializando o receiver");
        //
        ResponseMessageRepository repository = new ResponseMessageRepository();
        //
        Receiver receiver = new Receiver(repository);
        //
        Registry registry = LocateRegistry.createRegistry(1092);
        registry.bind("Receiver", receiver);
        registry.bind("Subject", receiver);
    }
}
