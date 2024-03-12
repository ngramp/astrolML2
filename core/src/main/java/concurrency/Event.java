package concurrency;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Graham Perry on 22/07/16.
 *
 * @author Graham Perry
 */
public class Event {
    private final SimpleStringProperty event;
    private final SimpleStringProperty time;
    private SimpleStringProperty progress;

    public Event(String time, String event) {
        this.event = new SimpleStringProperty(event);
        this.time = new SimpleStringProperty(time);
    }

    public Event(String event, String time, String progress) {
        this.event = new SimpleStringProperty(event);
        this.time = new SimpleStringProperty(time);
        this.progress = new SimpleStringProperty(progress);
    }

    public String getEvent() {

        return event.get();
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public SimpleStringProperty eventProperty() {
        return event;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getProgress() {
        return progress.get();
    }

    public void setProgress(String progress) {
        this.progress.set(progress);
    }

    public SimpleStringProperty progressProperty() {
        return progress;
    }
}
