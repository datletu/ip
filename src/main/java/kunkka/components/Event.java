package kunkka.components;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String name, String from, String to, boolean isDone) {
        super(name, "E", isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public long getDuration() {
        return ChronoUnit.DAYS.between(from, to);
    }

    public String getFromFormatted() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getToFormatted() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + name + " (from: " + getFromFormatted() + " to: " + getToFormatted() + ")";
    }
}