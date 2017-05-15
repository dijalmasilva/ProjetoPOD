package ifpb.edu.br.pod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class Channel {

    private int id;
    private String name;
    private List<IClientObserver> clientObservers;

    public Channel(int id, String name) {
        this.id = id;
        this.name = name;
        this.clientObservers = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IClientObserver> getClientObservers() {
        return clientObservers;
    }

    public void setClientObservers(List<IClientObserver> clientObservers) {
        this.clientObservers = clientObservers;
    }

    public void addNewSubscriber(IClientObserver clientObserver) {
        this.clientObservers.add(clientObserver);
    }

}
