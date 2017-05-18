package ifpb.edu.br.pod;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 13/05/17.
 */
public class Message implements Serializable {

    private String id;
    private String text;

    public Message() {
        id = generateId();
    }

    public Message(String text) {
        this.id = generateId();
        this.text = text;
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

    private String generateId() {
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        int monthValue = now.getMonthValue();
        int year = now.getYear();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int nano = now.getNano();
        StringBuilder string = new StringBuilder();
        string.append(dayOfMonth);
        string.append(monthValue);
        string.append(year);
        string.append(hour);
        string.append(minute);
        string.append(second);
        string.append(nano);
        System.out.println(string);
        return string.toString();
    }
}
