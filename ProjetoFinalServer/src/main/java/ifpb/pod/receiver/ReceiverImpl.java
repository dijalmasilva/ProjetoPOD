package ifpb.pod.receiver;

import ifpb.edu.br.pod.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class ReceiverImpl extends UnicastRemoteObject implements IReceiver {

    private IServer server;

    public ReceiverImpl() throws RemoteException, NotBoundException {

    }

    @Override
    public void delivery(Client client, Channel channel, Message msg) throws RemoteException {

        System.out.println("Receiver recebendo uma mensagem para encaminhar para o server");
        Registry registry = LocateRegistry.getRegistry(1099);
        try {
            this.server = (IServer) registry.lookup("Server");
            server.processMessageReceive(client, channel, msg);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public MessageResult result(String id) throws RemoteException {
        return null;
    }
}
