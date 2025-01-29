package kunkka.command;
import kunkka.tasklist.Tasklist;



public class List extends Command {

    public List() {
        super("list");
    }

    public void execute(Tasklist taskslist) {
        taskslist.printTasks();
    }

}
