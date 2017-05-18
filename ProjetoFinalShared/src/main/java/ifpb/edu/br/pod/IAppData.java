package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public interface IAppData extends Remote {

    void receiveMessageAndCreateNotification(MessageSended messageSended) throws RemoteException;

    List<Notification> listNotifications() throws RemoteException;

    Message getMessage(String idMessage) throws RemoteException;

    void removeNotification(Notification notification) throws RemoteException;
}
