package ifpb.pod.sender;

import ifpb.edu.br.pod.IReceiver;
import ifpb.edu.br.pod.MessageSended;
import ifpb.pod.repositories.MessageRepository;
import ifpb.pod.repositories.SendedMessageRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class SenderTask extends TimerTask {

    private final MessageRepository messageRepository;
    private final SendedMessageRepository sendedMessageRepository;

    public SenderTask(MessageRepository messageRepository, SendedMessageRepository sendedMessageRepository) {
        this.messageRepository = messageRepository;
        this.sendedMessageRepository = sendedMessageRepository;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {
            List<MessageSended> messages = messageRepository.getMessages();

            if (messages.size() > 0) {
                System.out.println("Há mensagens para serem enviadas!");
                Registry registry = LocateRegistry.getRegistry(1099);
                IReceiver receiver = (IReceiver) registry.lookup("Receiver");

                for (MessageSended m : messages) {

                    try {
                        receiver.delivery(m.getClient(), m.getChannel(), m.getMessage());
                        messageRepository.removeMessage(m);
                        sendedMessageRepository.addMessage(m);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("não há mensagens para enviar ao Receiver");
            }

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
