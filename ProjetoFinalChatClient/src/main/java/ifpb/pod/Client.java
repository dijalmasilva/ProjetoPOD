package ifpb.pod;

import ifpb.edu.br.pod.IClientObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Client extends UnicastRemoteObject implements IClientObserver {

    private String name;
    private String email;
    private boolean connected = false;

    public Client() throws RemoteException {
    }

    public Client(String name, String email) throws RemoteException {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public void login() throws RemoteException {
        this.connected = true;
    }

    @Override
    public void logout() throws RemoteException {
        this.connected = false;
    }

    @Override
    public void showNotify(String s) throws RemoteException {
        System.out.println(s);
    }

    @Override
    public String printClient() throws RemoteException {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", connected=" + connected +
                '}';
    }

}
