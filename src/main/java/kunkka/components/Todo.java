package kunkka.components;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param name Name of the Todo task.
     */
    public Todo(String name, boolean isDone) {
        super(name, "T", isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + name;
    }
}