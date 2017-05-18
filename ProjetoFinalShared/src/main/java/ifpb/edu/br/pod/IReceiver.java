package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public interface IReceiver extends Remote {

    void delivery(Client client, Channel channel, Message msg) throws RemoteException;

    MessageResult result(String id) throws RemoteException;
}
