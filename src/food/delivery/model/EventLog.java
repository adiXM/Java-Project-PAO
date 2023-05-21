package food.delivery.model;

import java.util.Date;
import java.util.UUID;

public class EventLog {

    private final String id = UUID.randomUUID().toString();

    private String action;

    private Date date;

    public EventLog(String action, Date date) {
        this.action = action;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
