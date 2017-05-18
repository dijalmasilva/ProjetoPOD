package ifpb.edu.br.pod;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class Notification implements Serializable {

    private String token;
    private Channel channel;
    private Client client;
    private LocalDateTime time;
    private String text;

    public Notification(Channel channel, Client client, LocalDateTime time, String text) {
        this.channel = channel;
        this.client = client;
        this.time = time;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
