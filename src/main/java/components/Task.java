package components;
public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    public Task(String name, String type, boolean isDone) {
        this.name = name;
        this.type = type;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + name;
    }
}
