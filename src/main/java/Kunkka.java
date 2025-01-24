import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import components.Task;

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
        String name = "Kunkka";
        String greeting = "Ahoy! I'm " + name + "\n" + "What can I do for you?";
        String farewell = "Farewell, matey! May the wind be at your back!";
        System.out.println(horizontalLine + "\n" + greeting + "\n" + horizontalLine + "\n");

        String input = new Scanner(System.in).nextLine();
        Task task = new Task(input);
        List<Task> tasks = new ArrayList<Task>();

        while (!task.getName().equals("bye")) {
            System.out.println(horizontalLine + "\n");

            //Handle list command
            if (task.getName().equals("list")) {
                printTasks(tasks);
            } 

            //Handle mark command
            else if (task.getName().matches("mark \\d+")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
            }

            //Handle unmark command
            else if (task.getName().matches("unmark \\d+")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks.get(index - 1).unmarkAsDone();
                System.out.println("Nice! I've unmarked this task:");
                System.out.println("  " + tasks.get(index - 1));
            }

            //Handle adding tasks
            else {
                tasks.add(task);
                System.out.println("added: " + task);
            }

            System.out.println(horizontalLine + "\n");
            input = new Scanner(System.in).nextLine();
            task = new Task(input);
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