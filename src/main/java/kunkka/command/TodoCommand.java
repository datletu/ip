package kunkka.command;
import java.util.*;

import kunkka.components.*;
import kunkka.tasklist.Tasklist;

/**
 * Represents a command to add a todo task to the tasklist.
 */
public class TodoCommand extends Command {
    protected String description;
    
    /**
     * Constructor for TodoCommand.
     * 
     * @param command The description of the todo task.
     */
    public TodoCommand(String command) {
        super("todo");
        this.description = command;
    }
    
    /**
     * Executes the command to add a todo task to the tasklist.
     * 
     * @param tasks The tasklist to which the todo task is to be added.
     */
    public String execute(Tasklist tasks) {
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
                return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
