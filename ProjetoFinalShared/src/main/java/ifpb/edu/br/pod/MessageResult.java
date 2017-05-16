package ifpb.edu.br.pod;

import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 16/05/17.
 */
public class MessageResult implements Serializable {

    private String id;
    private String hash;

    public MessageResult(String id, String hash) {
        this.hash = hash;
        this.id = id;
    }

    public MessageResult() {
    }

    public String getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

}
