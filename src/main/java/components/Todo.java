package components;

public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, "T", isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + name;
    }
}