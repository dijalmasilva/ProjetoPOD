package ifpb.edu.br.pod;

import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Message implements Serializable {

    private String id;
    private String text;

    public Message() {
    }

    public Message(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
