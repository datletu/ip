package kunkka.tasklist;

import java.util.List;

import kunkka.components.*;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class Tasklist {
    protected List<Task> tasks;
    
    /**
     * Constructs a Tasklist object.
     */
    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a Tasklist object with a list of tasks.
     * 
     * @param tasks List of tasks.
     */
    public Tasklist(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     * 
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     * 
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * 
     * @param index Index of the task to be deleted.
     */
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

    /**
     * Prints the list of tasks.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Finds tasks that contain the keyword.
     * 
     * @param keyword Keyword to search for.
     */
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

    /**
     * Unmarks a task as done.
     * 
     * @param index Index of the task to be unmarked.
     */
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
