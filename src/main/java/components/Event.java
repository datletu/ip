package components;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String name, String from, String to, boolean isDone) {
        super(name, "E", isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + name + " (from: " + from + " to: " + to + ")";
    }
}