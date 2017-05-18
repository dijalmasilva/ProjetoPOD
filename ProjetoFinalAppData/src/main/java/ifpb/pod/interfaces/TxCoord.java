package ifpb.pod.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public interface TxCoord extends Remote {

    void prepareAll() throws RemoteException;

    void commitAll() throws RemoteException;

    void rollbackAll() throws RemoteException;
}
