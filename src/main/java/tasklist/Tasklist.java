package tasklist;

import components.*;
import java.util.List;
import java.util.ArrayList;

public class Tasklist {
    protected List<Task> tasks;
    
    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    public Tasklist(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Zero or Negative)");
            }
            else {
                Task task = tasks.remove(index - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Zero or negative)");
            }
            else {
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
            }

        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());

        }
    }

    public void unmarkTaskAsDone(int index) {
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Negative)");
            }
            else {
                tasks.get(index - 1).unmarkAsDone();
                System.out.println("Nice! I've unmarked this task:");
                System.out.println("  " + tasks.get(index - 1));
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
        }
    }

}
