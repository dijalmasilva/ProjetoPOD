package ifpb.pod.server;

import ifpb.edu.br.pod.Channel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class ChannelRepository {

    private int idGenerated;
    private List<Channel> channels;

    public ChannelRepository() {
        this.idGenerated = 0;
        this.channels = new LinkedList<>();
    }

    public void addChannel(Channel channel) {
        this.channels.add(channel);
        System.out.println("Adicionado um novo grupo: " + channel.getName());
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public int getNextId() {
        return ++idGenerated;
    }

    public Channel findChannelById(int id) {

        for (Channel c : channels) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }
}
