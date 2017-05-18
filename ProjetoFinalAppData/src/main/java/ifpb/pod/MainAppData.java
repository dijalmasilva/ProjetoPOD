package ifpb.pod;

import ifpb.edu.br.pod.IAppData;
import ifpb.edu.br.pod.Message;
import ifpb.edu.br.pod.Notification;
import ifpb.pod.appdata.AppDataImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class MainAppData {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        List<Notification> notifications = new LinkedList<>();
        List<Message> messages = new LinkedList<>();
        IAppData appData = new AppDataImpl(notifications, messages);
        Registry registry = LocateRegistry.createRegistry(1093);
        registry.bind("AppData", appData);
        System.out.println("ServerAppData iniciado!");
    }
}
