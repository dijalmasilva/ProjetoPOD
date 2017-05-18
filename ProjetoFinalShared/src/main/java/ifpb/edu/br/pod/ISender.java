package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public interface ISender extends Remote {

    void sendMessage(Client client, Channel channel, Message message) throws RemoteException;

    boolean getNotification(Notification notification) throws RemoteException;
}
