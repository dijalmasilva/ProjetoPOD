package ifpb.edu.br.pod;

import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Client implements Serializable {

    private String name;
    private String email;
    private boolean connected = false;

    public Client() {
    }

    public Client(String name, String email) {
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

    public void login() {
        this.connected = true;
    }

    public void logout() {
        this.connected = false;
    }

    public void showNotify(String s) {
        System.out.println(s);
    }

    public String printClient() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", connected=" + connected +
                '}';
    }
}
