package components;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name, "E");
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + name + " (from: " + from + " to: " + to + ")";
    }
}