package kunkka.parser;

import kunkka.components.Task;
import kunkka.components.Todo;
import kunkka.components.Deadline;
import kunkka.components.Event;
import kunkka.components.KunkkaException;

import kunkka.command.Command;
import kunkka.command.List;
import kunkka.command.Mark;
import kunkka.command.Unmark;
import kunkka.command.TodoCommand;
import kunkka.command.DeadlineCommand;
import kunkka.command.EventCommand;
import kunkka.command.Delete;
import kunkka.command.Find;
import kunkka.command.InvalidCommand;

/**
 * Parser class to parse user input and return the corresponding command or task object
 */
public class Parser {
    /**
     * Parses a task from a string
     * @param taskLine the string to parse
     * @return the task object
     */
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

    /**
     * Parses a command from a string
     * @param command the string to parse
     * @return the command object
     */
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

        //Handle find command
        else if (command.matches("find .*")) {
            return new Find(command.split(" ", 2)[1]);
        }

        //Handle invalid command
        else {
            try {
                throw new KunkkaException("Error: Invalid command");
            }
            catch (KunkkaException e) {
                System.out.println(e.getMessage());
                return new InvalidCommand();
            }
        }           
    }
}

