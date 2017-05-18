package ifpb.pod.repositories;

import ifpb.edu.br.pod.MessageSended;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class SendedMessageRepository {


    private List<MessageSended> messages;

    public SendedMessageRepository(List<MessageSended> messages) {
        this.messages = messages;
    }

    public List<MessageSended> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageSended> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageSended message) {
        this.messages.add(message);
    }

    public void removeMessage(MessageSended message) {
        this.messages.remove(message);
    }
}
