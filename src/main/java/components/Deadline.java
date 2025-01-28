package components;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String name, String by, boolean isDone) {
        super(name, "D", isDone);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return by;
    }

    public String getByFormatted() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + name + " (by: " + getByFormatted() + ")";
    }
}