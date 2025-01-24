import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import components.*;

public class Kunkka {
    public static void main(String[] args) {
        String logo = " _______  ______   _______ _________ _______  _______  _          _                 _        _        _        _______ \n"
                    + "(  ___  )(  __  \\ (       )\\__   __/(  ____ )(  ___  )( \\        | \\    /\\|\\     /|( (    /|| \\    /\\| \\    /\\(  ___  )\n"
                    + "| (   ) || (  \\  )| () () |   ) (   | (    )|| (   ) || (        |  \\  / /| )   ( ||  \\  ( ||  \\  / /|  \\  / /| (   ) |\n"
                    + "| (___) || |   ) || || || |   | |   | (____)|| (___) || |        |  (_/ / | |   | ||   \\ | ||  (_/ / |  (_/ / | (___) |\n"
                    + "|  ___  || |   | || |(_)| |   | |   |     __)|  ___  || |        |   _ (  | |   | || (\\ \\) ||   _ (  |   _ (  |  ___  |\n"
                    + "| (   ) || |   ) || |   | |   | |   | (\\ (   | (   ) || |        |  ( \\ \\ | |   | || | \\   ||  ( \\ \\ |  ( \\ \\ | (   ) |\n"
                    + "| )   ( || (__/  )| )   ( |___) (___| ) \\ \\__| )   ( || (____/\\  |  /  \\ \\| (___) || )  \\  ||  /  \\ \\|  /  \\ \\| )   ( |\n"
                    + "|/     \\|(______/ |/     \\|\\_______/|/   \\__/|/     \\|(_______/  |_/    \\/(_______)|/    )_)|_/    \\/|_/    \\/|/     \\|\n";
        System.out.println(logo);
        String horizontalLine = "____________________________________________________________";
        String botName = "Kunkka";
        String greeting = "Ahoy! I'm " + botName + "\n" + "What can I do for you?";
        String farewell = "Farewell, matey! May the wind be at your back!";
        System.out.println(horizontalLine + "\n" + greeting + "\n" + horizontalLine + "\n");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        List<Task> tasks = new ArrayList<Task>();

        //Processing commands
        while (!command.equals("bye")) {
            System.out.println(horizontalLine + "\n");

            //Handle list command
            if (command.equals("list")) {
                printTasks(tasks);
            } 

            //Handle mark command
            else if (command.matches("mark -?\\d+")) {
                try {
                    int index = Integer.parseInt(command.split(" ")[1]);
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

            //Handle unmark command
            else if (command.matches("unmark \\d+")) {
                int index = Integer.parseInt(command.split(" ")[1]);
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

            //Handle todo command
            else if (command.matches("todo .*")) {
                Task task = new Todo(command.split(" ", 2)[1]);
                try {
                    if (task.getName().trim().equals("")) {
                        throw new KunkkaException("Error: Task name cannot be empty");
                    }
                    else {
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                catch (KunkkaException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Handle deadline command
            else if (command.matches("deadline .* /by .*")) {
                String[] parts = command.split("/by");
                Task task = new Deadline(parts[0].split(" ", 2)[1].trim(), parts[1].trim());
                try {
                    if (task.getName().equals("")) {
                        throw new KunkkaException("Error: Task name cannot be empty");
                    }
                    else if (parts[1].trim().equals("")) {
                        throw new KunkkaException("Error: Deadline cannot be empty");
                    }
                    else {
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                catch (KunkkaException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Handle event command
            else if (command.matches("event .* /from.*/to.*")) {
                String name = command.split("/from")[0].split(" ", 2)[1].trim();
                String from = command.split("/from")[1].split("/to")[0].trim();
                String to = command.split("/to")[1].trim();
                try {
                    if (name.trim().equals("")) {
                        throw new KunkkaException("Error: Task name cannot be empty");
                    }
                    else if (from.trim().equals("")) {
                        throw new KunkkaException("Error: Event start time cannot be empty");
                    }
                    else if (to.trim().equals("")) {
                        throw new KunkkaException("Error: Event end time cannot be empty");
                    }
                    else {
                        Task task = new Event(name, from, to);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                catch (KunkkaException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Handle delete command
            else if (command.matches("delete -?\\d+")) {
                int index = Integer.parseInt(command.split(" ")[1]);
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

            //Handle invalid command
            else {
                try {
                    throw new KunkkaException("Error: Invalid command");
                }
                catch (KunkkaException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println(horizontalLine + "\n");
            command = sc.nextLine();
        }

        //Handle bye command
        System.out.println(horizontalLine + "\n" + farewell + "\n" + horizontalLine);
    }

    static void printTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}