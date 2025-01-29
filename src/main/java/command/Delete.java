package command;
import tasklist.Tasklist;

public class Delete extends Command {
    protected int index;
    
    public Delete(int index) {
        super("delete");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }   

    public void execute(Tasklist tasklist) {
        tasklist.deleteTask(index);
    }    
}
