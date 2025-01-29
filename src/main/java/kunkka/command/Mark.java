package kunkka.command;
import kunkka.tasklist.Tasklist;

public class Mark extends Command {
    protected int index;
    
    public Mark(int index) {
        super("mark");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }   

    public void execute(Tasklist tasklist) {
        tasklist.markTaskAsDone(index);
    }
}
