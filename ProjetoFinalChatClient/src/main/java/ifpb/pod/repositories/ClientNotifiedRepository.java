package ifpb.pod.repositories;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 17/05/17.
 */
public class ClientNotifiedRepository {

    private List<String> tokens;

    public ClientNotifiedRepository(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public void addToken(String token) {
        this.tokens.add(token);
    }

    public void removeToken(String token) {
        this.tokens.remove(token);
    }
}
