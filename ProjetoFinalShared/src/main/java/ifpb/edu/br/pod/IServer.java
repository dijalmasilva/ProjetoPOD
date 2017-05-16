package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public interface IServer extends Remote {

    boolean addObserver(Client clientObserver) throws RemoteException;

    boolean registerChannel(Client clientObserver, String nameChannel) throws RemoteException;

    boolean registerInChannel(Client clientObserver, int idChannel) throws RemoteException;

    List<Channel> listChannels() throws RemoteException;

    MessageResult processMessageReceive(Message message) throws RemoteException;
}
