package kunkka.parser;

import kunkka.command.*;
import kunkka.components.*;

public class Parser {
    public static Task parseTask(String taskLine) {
        String[] parts = taskLine.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String name = parts[2];
        switch (type) {
            case "T":
                return new Todo(name, isDone);
            case "D":
                return new Deadline(name, parts[3], isDone);
            case "E":
                return new Event(name, parts[3], parts[4], isDone);
            default:
                return null;
        }
    }

    public static Command parseCommand(String command) {
        //Handle list command
        if (command.equals("list")) {
            return new List();
        } 

        //Handle mark command
        else if (command.matches("mark -?\\d+")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            return new Mark(index);
        }

        //Handle unmark command
        else if (command.matches("unmark \\d+")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            return new Unmark(index);
        }

        //Handle todo command
        else if (command.matches("todo .*")) {
            return new TodoCommand(command.split(" ", 2)[1]);
        }

        //Handle deadline command
        else if (command.matches("deadline .* /by .*")) {
            String[] parts = command.split("/by");
            return new DeadlineCommand(parts[0].split(" ", 2)[1].trim(), parts[1].trim());
        }

        //Handle event command
        else if (command.matches("event .* /from.*/to.*")) {
            String name = command.split("/from")[0].split(" ", 2)[1].trim();
            String from = command.split("/from")[1].split("/to")[0].trim();
            String to = command.split("/to")[1].trim();
            return new EventCommand(name, from, to);
        }

        //Handle delete command
        else if (command.matches("delete -?\\d+")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            return new Delete(index);
        }

        //Handle invalid command
        else {
            try {
                throw new KunkkaException("Error: Invalid command");
            }
            catch (KunkkaException e) {
                System.out.println(e.getMessage());
                return null;
            }
            
        }           
    }
}

