package ifpb.edu.br.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public interface IClientObserver extends Remote {

    void login() throws RemoteException;

    void logout() throws RemoteException;

    void showNotify(String notify) throws RemoteException;

    String printClient() throws RemoteException;
}
