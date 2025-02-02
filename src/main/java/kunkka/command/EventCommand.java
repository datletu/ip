package kunkka.command;
import kunkka.components.*;
import kunkka.tasklist.Tasklist;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    
    protected String description;
    protected String from;
    protected String to;

    /**
     * Constructs an EventCommand object.
     * 
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public EventCommand(String description, String from, String to){
        super("event");
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command to add an event task to the task list.
     * 
     * @param tasks Tasklist object that stores the list of tasks.
     */
    public void execute(Tasklist tasks) {
        try {
            if (description.trim().equals("")) {
                throw new KunkkaException("Error: Task description cannot be empty");
            }
            else if (from.trim().equals("")) {
                throw new KunkkaException("Error: Event start time cannot be empty");
            }
            else if (to.trim().equals("")) {
                throw new KunkkaException("Error: Event end time cannot be empty");
            }
            else {
                Event task = new Event(description, from, to, false);
                if (task.getDuration() < 0) {
                    throw new KunkkaException("Error: Event end time must be after start time");
                }
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
