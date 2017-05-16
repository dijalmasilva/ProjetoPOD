package ifpb.pod.sender;

import ifpb.edu.br.pod.ISender;
import ifpb.edu.br.pod.Message;
import ifpb.edu.br.pod.MessageResult;
import ifpb.pod.sender.repositories.MessageRepository;
import ifpb.pod.sender.repositories.MessageResultRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class SenderImpl extends UnicastRemoteObject implements ISender {

    private final MessageRepository repository;
    private final MessageResultRepository resultRepository;

    public SenderImpl(MessageRepository rep, MessageResultRepository resultRep) throws RemoteException {
        this.repository = rep;
        this.resultRepository = resultRep;
    }

    @Override
    public void sendMessage(Message dto) throws RemoteException {
        //armazenar temporariamente a mensagem
        repository.add(dto);
    }

    @Override
    public MessageResult getMessage(String id) throws RemoteException {
        //recuperar a mensagem no repositório
        MessageResult result = resultRepository.get(id);
        if (result != null) resultRepository.remove(result);
        return result;
    }
}
