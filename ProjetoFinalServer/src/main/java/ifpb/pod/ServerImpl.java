package ifpb.pod;

import ifpb.edu.br.pod.Channel;
import ifpb.edu.br.pod.IClientObserver;
import ifpb.edu.br.pod.IServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class ServerImpl extends UnicastRemoteObject implements IServer {

    private List<IClientObserver> clientObservers;
    private ChannelRepository channelRepository;

    ServerImpl() throws RemoteException {
        clientObservers = new LinkedList<>();
        channelRepository = new ChannelRepository();
    }

    @Override
    public boolean addObserver(IClientObserver clientObserver) throws RemoteException {
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
    public boolean registerChannel(String nameChannel) throws RemoteException {
        try {
            System.out.println("Criando novo grupo:" + nameChannel);
            Channel channel = new Channel(channelRepository.getNextId(), nameChannel);
            channelRepository.addChannel(channel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Channel> listChannels() throws RemoteException {
        return channelRepository.getChannels();
    }
}
