package command;
import tasklist.*;

public class Unmark extends Command {
    private int taskNumber;

    public Unmark(int taskNumber) {
        super("unmark");
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void execute(Tasklist tasklist) {
        tasklist.unmarkTaskAsDone(taskNumber);
    }
    
}
