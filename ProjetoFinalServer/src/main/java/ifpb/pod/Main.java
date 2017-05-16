package ifpb.pod;

import ifpb.edu.br.pod.IServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {

        IServer register = new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Register", register);
        System.out.println("Servidor iniciado!");
    }
}
