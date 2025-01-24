package components;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name, "D");
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + name + " (by: " + by + ")";
    }
}