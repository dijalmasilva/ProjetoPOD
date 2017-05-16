package ifpb.pod;

import ifpb.edu.br.pod.Client;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {

        Client clientObserver = new Client("Dijalma", "dijalmacz@gmail.com");
        Client clientObserver2 = new Client("Teste", "teste@gmail.com");
        TaskManager taskManager = new TaskManager();
        boolean c = taskManager.registerClient(clientObserver);
        boolean c1 = taskManager.registerClient(clientObserver2);
        if (c && c1) {
            System.out.println("Clientes cadastrados com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar os clientes!");
        }

        taskManager.registerChannel(clientObserver, "Grupo 1");
        taskManager.registerChannel(clientObserver, "Grupo 2");
        taskManager.registerChannel(clientObserver, "Grupo 3");
        taskManager.registerChannel(clientObserver, "Grupo 4");

        taskManager.listChannels();
        System.out.println();

        taskManager.registerInChannel(clientObserver2, 2);
        taskManager.registerInChannel(clientObserver2, 3);

        taskManager.listChannels();
    }
}
