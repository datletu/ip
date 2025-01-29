package command;
import tasklist.Tasklist;

public abstract class Command {
    protected String type;

    public Command(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract void execute(Tasklist tasks);

}
