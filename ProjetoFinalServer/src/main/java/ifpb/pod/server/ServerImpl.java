package ifpb.pod.server;

import ifpb.edu.br.pod.*;

import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class ServerImpl extends UnicastRemoteObject implements IServer {

    private List<Client> clientObservers;
    private ChannelRepository channelRepository;

    public ServerImpl(List<Client> clients, ChannelRepository channelRepository) throws RemoteException {
        this.clientObservers = clients;
        this.channelRepository = channelRepository;
    }

    @Override
    public boolean addObserver(Client clientObserver) throws RemoteException {
        try {
            System.out.println("Adicionando cliente observer: " + clientObserver.printClient());
            this.clientObservers.add(clientObserver);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean registerChannel(Client clientObserver, String nameChannel) throws RemoteException {
        try {
            System.out.println("Criando novo grupo:" + nameChannel);
            Channel channel = new Channel(channelRepository.getNextId(), nameChannel);
            channel.addNewSubscriber(clientObserver);
            channelRepository.addChannel(channel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean registerInChannel(Client clientObserver, int idChannel) throws RemoteException {
        Channel channelById = channelRepository.findChannelById(idChannel);
        if (channelById == null) {
            System.out.println("Grupo não existe!");
            return false;
        } else {
            channelById.addNewSubscriber(clientObserver);
            return true;
        }
    }

    @Override
    public List<Channel> listChannels() throws RemoteException {
        return channelRepository.getChannels();
    }

    @Override
    public void processMessageReceive(Client client, Channel channel, Message msg) throws RemoteException {
        System.out.println("Server recebeu a mensagem do cliente: " + client.getName());
        System.out.println("Mensagem para o canal: " + channel.getName());
        System.out.println("Mensagem recebida: " + msg.getText());
        System.out.println();

        Registry registry = LocateRegistry.getRegistry(1093);
        try {
            IAppData appData = (IAppData) registry.lookup("AppData");
            appData.receiveMessageAndCreateNotification(new MessageSended(client, channel, msg));
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
