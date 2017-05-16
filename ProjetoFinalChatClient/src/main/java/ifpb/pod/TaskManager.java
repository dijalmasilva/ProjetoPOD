package ifpb.pod;

import ifpb.edu.br.pod.Channel;
import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.IServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class TaskManager {

    private IServer server;

    TaskManager() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        server = (IServer) registry.lookup("Register");
    }

    boolean registerClient(Client clientObserver) {

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

    public void listChannels() throws RemoteException {
        printChannels(server.listChannels());
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
