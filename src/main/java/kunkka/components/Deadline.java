package kunkka.components;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param name Name of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String name, String by, boolean isDone) {
        super(name, "D", isDone);
        assert by != null : "Deadline cannot be null";
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructor for Deadline.
     *
     * @param name Name of the deadline task.
     * @param by Deadline of the task.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the deadline of the task in formatted string.
     *
     * @return Deadline of the task in formatted string.
     */
    public String getByFormatted() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + name + " (by: " + getByFormatted() + ")";
    }
}