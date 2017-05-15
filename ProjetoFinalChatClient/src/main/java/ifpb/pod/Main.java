package ifpb.pod;

import ifpb.edu.br.pod.IClientObserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Registry registry = LocateRegistry.createRegistry(1098);
        registry.bind("Client", new Client());

        IClientObserver clientObserver = new Client("Dijalma", "dijalmacz@gmail.com");
        TaskManager taskManager = new TaskManager();
        boolean registerResult = taskManager.registerClient(clientObserver);
        if (registerResult) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o cliente!");
        }
    }
}
