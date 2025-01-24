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
        String command = sc.nextLine().trim();
        List<Task> tasks = new ArrayList<Task>();

        //Processing commands
        while (!command.equals("bye")) {
            System.out.println(horizontalLine + "\n");

            //Handle list command
            if (command.equals("list")) {
                printTasks(tasks);
            } 

            //Handle mark command
            else if (command.matches("mark \\d+")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
            }

            //Handle unmark command
            else if (command.matches("unmark \\d+")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                tasks.get(index - 1).unmarkAsDone();
                System.out.println("Nice! I've unmarked this task:");
                System.out.println("  " + tasks.get(index - 1));
            }

            //Handle todo command
            else if (command.matches("todo .+")) {
                Task task = new Todo(command.split(" ", 2)[1]);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            //Handle deadline command
            else if (command.matches("deadline .+ /by .+")) {
                String[] parts = command.split(" /by ");
                Task task = new Deadline(parts[0].split(" ", 2)[1], parts[1]);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            //Handle event command
            else if (command.matches("event .+ /from .+ /to .+")) {
                String name = command.split(" /from ")[0].split(" ", 2)[1];
                String from = command.split(" /from ")[1].split(" /to ")[0];
                String to = command.split(" /to ")[1];
                Task task = new Event(name, from, to);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            //Handle adding tasks
            else {
                System.out.println("Error: Invalid command");
            }

            System.out.println(horizontalLine + "\n");
            command = sc.nextLine().trim();
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