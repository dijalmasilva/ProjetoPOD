package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public interface ISender extends Remote {

    void sendMessage(Message message) throws RemoteException;

    MessageResult getMessage(String id) throws RemoteException;
}
