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
 * Parser class to parse user input and return the corresponding command or task object.
 */
public class Parser {
    /**
     * Parses a task from a string.
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
     * Parses a command from a string.
     * @param command the string to parse
     * @return the command object
     */
    public static Command parseCommand(String command) {
        if (command.equals("list")) {
            return parseListCommand();
        } else if (command.matches("mark -?\\d+")) {
            return parseMarkCommand(command);
        } else if (command.matches("unmark \\d+")) {
            return parseUnmarkCommand(command);
        } else if (command.matches("todo .*")) {
            return parseTodoCommand(command);
        } else if (command.matches("deadline .* /by .*")) {
            return parseDeadlineCommand(command);
        } else if (command.matches("event .* /from.*/to.*")) {
            return parseEventCommand(command);
        } else if (command.matches("delete -?\\d+")) {
            return parseDeleteCommand(command);
        } else if (command.matches("find .*")) {
            return parseFindCommand(command);
        } else {
            return parseInvalidCommand();
        }
    }

    /**
     * Parses a list command.
     * @return the list command object
     */
    private static Command parseListCommand() {
        return new List();
    }

    /**
     * Parses a mark command.
     * @param command the string to parse
     * @return the mark command object
     */
    private static Command parseMarkCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Mark(index);
    }

    /**
     * Parses an unmark command.
     * @param command the string to parse
     * @return the unmark command object
     */
    private static Command parseUnmarkCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Unmark(index);
    }

    /**
     * Parses a todo command.
     * @param command the string to parse
     * @return the todo command object
     */
    private static Command parseTodoCommand(String command) {
        return new TodoCommand(command.split(" ", 2)[1]);
    }

    /**
     * Parses a deadline command.
     * @param command the string to parse
     * @return the deadline command object
     */
    private static Command parseDeadlineCommand(String command) {
        String[] parts = command.split("/by");
        return new DeadlineCommand(parts[0].split(" ", 2)[1].trim(), parts[1].trim());
    }

    /**
     * Parses an event command.
     * @param command the string to parse
     * @return the event command object
     */
    private static Command parseEventCommand(String command) {
        String name = command.split("/from")[0].split(" ", 2)[1].trim();
        String from = command.split("/from")[1].split("/to")[0].trim();
        String to = command.split("/to")[1].trim();
        return new EventCommand(name, from, to);
    }

    /**
     * Parses a delete command.
     * @param command the string to parse
     * @return the delete command object
     */
    private static Command parseDeleteCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Delete(index);
    }

    /**
     * Parses a find command.
     * @param command the string to parse
     * @return the find command object
     */
    private static Command parseFindCommand(String command) {
        return new Find(command.split(" ", 2)[1]);
    }

    /**
     * Parses an invalid command.
     * @return the invalid command object
     */
    private static Command parseInvalidCommand() {
        try {
            throw new KunkkaException("Error: Invalid command");
        } catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand();
        }
    }
}