package ifpb.pod.interfaces;

import java.rmi.RemoteException;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public interface TxOperation {

    void prepare() throws RemoteException;

    void commit() throws RemoteException;

    void rollback() throws RemoteException;
}
