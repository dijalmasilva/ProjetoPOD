package ifpb.edu.br.pod;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 15/05/17.
 */
public class Channel implements Serializable {

    private int id;
    private String name;
    private List<Client> clientObservers;

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

    public List<Client> getClientObservers() {
        return clientObservers;
    }

    public void setClientObservers(List<Client> clientObservers) {
        this.clientObservers = clientObservers;
    }

    public void addNewSubscriber(Client clientObserver) {
        this.clientObservers.add(clientObserver);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
