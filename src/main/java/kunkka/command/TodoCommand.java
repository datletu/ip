package kunkka.command;
import java.util.*;

import kunkka.components.*;
import kunkka.tasklist.Tasklist;

public class TodoCommand extends Command {
    protected String description;
    
    public TodoCommand(String command) {
        super("todo");
        this.description = command;
    }
    
    public void execute(Tasklist tasks) {
        try {
            if (description.equals("")) {
                throw new KunkkaException("Error: Task name cannot be empty");
            }
            else {
                Task task = new Todo(description, false);
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
