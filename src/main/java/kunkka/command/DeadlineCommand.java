package kunkka.command;
import kunkka.components.*;
import kunkka.tasklist.*;

public class DeadlineCommand extends Command {
    
    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        super("deadline");
        this.description = description;
        this.by = by;
    }

    public void execute(Tasklist tasks) {
        try {
            if (description.equals("")) {
                throw new KunkkaException("Error: Task name cannot be empty");
            }
            else if (by.equals("")) {
                throw new KunkkaException("Error: Deadline cannot be empty");
            }
            else {
                Task task = new Deadline(description, by, false);
                tasks.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
        }
    }
}
