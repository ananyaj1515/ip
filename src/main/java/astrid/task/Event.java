package astrid.task;

import astrid.AstridException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a specific start and end date/time.
 *
 * An Event extends Task and tracks both when an event starts and when it ends.
 * It uses a specific date/time format for parsing and displaying event details.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with a description and start/end date-times.
     *
     * @param desc the description of the event
     * @param start the start date and time in format "dd/MM/yyyy HHmm"
     * @param end the end date and time in format "dd/MM/yyyy HHmm"
     */
    public Event(String desc, String start, String end) throws AstridException {
        super(desc);
        try {
            this.start = LocalDateTime.parse(start, INPUT_FORMAT);
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
        } catch (Exception e) {
            // Catch parsing errors and throw your custom exception
            throw new AstridException("Invalid date/time format. Please use dd/MM/yyyy HHmm.");
        }
    }


    /**
     * Returns a formatted string representation of the event.
     *
     * Format: "[E][status] description (from: start to: end)"
     * where status is "[X]" if done or "[ ]" if not done.
     *
     * @return formatted string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(OUTPUT_FORMAT)
                + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the storage format representation of the event.
     *
     * Format: "E | status | description | start-end"
     * where status is "1" if done or "0" if not done.
     *
     * @return storage format string for persisting the event to file
     */
    @Override
    public String storeFormat() {
        return "E | " + this.getStoreStatus() + " | "
                + this.getDesc() + " | " + start.format(INPUT_FORMAT)
                + "-" + end.format(INPUT_FORMAT);
    }

    /**
     * Checks if the event is happening today or has today as one of its dates.
     *
     * Returns this event if either the start or end date matches today's date,
     * otherwise returns null.
     *
     * @return this Event if it occurs today, null otherwise
     */
    public Task today() {
        LocalDate today = LocalDate.now();
        if (this.start.toLocalDate().equals(today) || this.end.toLocalDate().equals(today)) {
            return this;
        }
        return null;
    }
}
