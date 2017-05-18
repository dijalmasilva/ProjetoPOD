package ifpb.pod.chatclient;

import ifpb.edu.br.pod.Client;
import ifpb.edu.br.pod.IAppData;
import ifpb.edu.br.pod.Message;
import ifpb.pod.repositories.ClientNotifiedRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class SearchMessages extends TimerTask {

    private final ClientNotifiedRepository clientNotifiedRepository;
    private final List<Client> clients;

    public SearchMessages(ClientNotifiedRepository clientNotifiedRepository, List<Client> clients) {
        this.clientNotifiedRepository = clientNotifiedRepository;
        this.clients = clients;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {

        try {
            List<String> tokens = clientNotifiedRepository.getTokens();

            if (tokens.size() > 0) {
                System.out.println("Há mensagens a serem recuperadas!");
                Registry registry = LocateRegistry.getRegistry(1093);
                IAppData appData = (IAppData) registry.lookup("AppData");
                for (String token : tokens) {
                    Message message = appData.getMessage(token);
                    if (message != null) {
                        showMessage(message);
                    }
                    clientNotifiedRepository.removeToken(token);
                }
            } else {
                System.out.println("Não há mensagens a serem recuperadas em AppData!");
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(Message message) {
        String[] split = message.getId().split(":");

        Client client = findClientByEmail(split[1]);
        if (client != null) {
            client.showNotify("Cliente " + client.getEmail() + " recebeu a mensagem abaixo: ");
            client.showNotify("Mensagem recebida: " + message.getText());
        }

    }

    private Client findClientByEmail(String email) {
        for (Client c : clients) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }

        return null;
    }
}
