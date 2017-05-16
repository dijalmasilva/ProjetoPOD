package ifpb.pod.receiver;

import ifpb.edu.br.pod.MessageResult;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class ResponseMessageRepository {

    private volatile List<MessageResult> list = new LinkedList<>();

    public void add(MessageResult msg) {
        System.out.println("Adicionando uma mensagem de resposta: " + msg.getId());
        list.add(msg);
    }

    public void remove(MessageResult msg) {
        System.out.println("Removendo uma mensagem de resposta: " + msg.getId());
        for (int i = 0; i < list.size(); i++) {
            MessageResult message = list.get(i);
            if (msg.getId().equals(message.getId())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<MessageResult> list() {
        return Collections.unmodifiableList(list);
    }

    public MessageResult get(String id) {
        for (MessageResult messageResult : list) {
            if (messageResult.getId().equals(id)) {
                return messageResult;
            }
        }
        return null;
    }
}
