package ifpb.pod.server;

import ifpb.edu.br.pod.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
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

    ServerImpl() throws RemoteException {
        clientObservers = new LinkedList<>();
        channelRepository = new ChannelRepository();
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
    public MessageResult processMessageReceive(Message msg) throws RemoteException {
        //
        MessageDigest msd;
        try {
            msd = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RemoteException("Erro de MD5", e);
        }
        //
        byte[] bhash = msd.digest(msg.getText().getBytes());
        BigInteger bi = new BigInteger(bhash);
        //
        MessageResult result = new MessageResult(msg.getId(), bi.toString(16));
        //
        System.out.println(msg.getId() + " " + msg.getText());
        //
        return result;
    }
}
