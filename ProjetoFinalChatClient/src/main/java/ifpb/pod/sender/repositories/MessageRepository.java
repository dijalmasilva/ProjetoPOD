package ifpb.pod.sender.repositories;

import ifpb.edu.br.pod.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class MessageRepository {

    private List<Message> list = new LinkedList<>();

    public void add(Message msg) {
        System.out.println("Adicionando uma mensagem: " + msg.getId());
        list.add(msg);
    }

    public void remove(Message msg){
        System.out.println("Removendo uma mensagem: " + msg.getId());
        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);
            if (msg.getId().equals(message.getId())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<Message> list() {
        return Collections.unmodifiableList(list);
    }
}
