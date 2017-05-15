package ifpb.pod;

import ifpb.edu.br.pod.IClientObserver;
import ifpb.edu.br.pod.IServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class TaskManager {

    private IServer server;

    TaskManager() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        server = (IServer) registry.lookup("Register");
    }

    boolean registerClient(IClientObserver clientObserver) {

        try {
            if (clientObserver != null) {
                return server.addObserver(clientObserver);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean registerChannel(String nameChannel) {
        try {
            if (nameChannel != null) {
                return server.registerChannel(nameChannel);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }
}
