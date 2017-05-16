package ifpb.pod.sender.repositories;

import ifpb.edu.br.pod.MessageResult;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class MessageResultRepository {

    private List<MessageResult> list = new LinkedList<>();

    public void add(MessageResult msg) {
        System.out.println("Adicionando uma mensagem de resultado: " + msg.getHash());
        list.add(msg);
    }

    public void remove(MessageResult msg) {
        System.out.println("Removendo uma mensagem de resultado: " + msg.getHash());
        for (int i = 0; i < list.size(); i++) {
            MessageResult message = list.get(i);
            if (msg.getHash().equals(message.getHash())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<MessageResult> list() {
        return Collections.unmodifiableList(list);
    }

    public MessageResult get(String id) {
        for (int i = 0; i < list().size(); i++) {
            MessageResult message = list.get(i);
            if (id.equals(message.getId())) {
                return message;
            }
        }
        return null;
    }
}
