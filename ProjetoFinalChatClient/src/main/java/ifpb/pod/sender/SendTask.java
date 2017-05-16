package ifpb.pod.sender;

import ifpb.edu.br.pod.IReceiver;
import ifpb.edu.br.pod.Message;
import ifpb.pod.sender.repositories.MessageRepository;
import ifpb.pod.sender.repositories.SendedMessageRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class SendTask extends TimerTask {

    private final MessageRepository repository;
    private final SendedMessageRepository sendedRepository;

    public SendTask(MessageRepository repository, SendedMessageRepository sendedRepository) {
        this.repository = repository;
        this.sendedRepository = sendedRepository;
    }

    @Override
    public void run() {
        //
        try {
            //localizar as mensagens para serem enviadas
            List<Message> list = repository.list();
            //checar se existe mensagem
            if (list.size() > 0) {
                Registry registry = LocateRegistry.getRegistry(10991);
                //fazer o lookup
                IReceiver receiver = (IReceiver) registry.lookup("Receiver");
                //
                for (int i = 0; i < list.size(); i++) {
                    //
                    Message m = list.get(i);
                    //
                    try {
                        receiver.delivery(m);
                        repository.remove(m);
                        sendedRepository.add(m);
                        break;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }

}
