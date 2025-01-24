import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

        List<String> tasks = new ArrayList<String>();

        while (!input.equals("bye")) {
            System.out.println(horizontalLine + "\n");

            if (input.equals("list")) {
                printTasks(tasks);
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }

            System.out.println(horizontalLine + "\n");
            input = new Scanner(System.in).nextLine();
        }
        System.out.println(horizontalLine + "\n" + farewell + "\n" + horizontalLine);
    }

    static void printTasks(List<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}