package astrid.task;

import astrid.AstridException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a specific due date and time.
 *
 * A Deadline extends Task and tracks when the task must be completed by.
 * It uses a specific date/time format for parsing and displaying deadline details.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    private LocalDateTime finishBy;

    /**
     * Constructs a Deadline task with a description and a due date/time.
     *
     * @param desc the description of the deadline task
     * @param finishBy the due date and time in format "dd/MM/yyyy HHmm"
     */
    public Deadline(String desc, String finishBy) throws AstridException {
        super(desc);
        try {
            this.finishBy = LocalDateTime.parse(finishBy, INPUT_FORMAT);
        } catch (Exception e) {
            throw new AstridException("Invalid date/time format. Please use dd/MM/yyyy HHmm.");
        }
    }

    /**
     * Checks if the deadline is due today.
     *
     * Returns this deadline if the due date matches today's date,
     * otherwise returns null.
     *
     * @return this Deadline if it is due today, null otherwise
     */
    @Override
    public Task today() {
        LocalDate today = LocalDate.now();
        if (this.finishBy.toLocalDate().equals(today)) {
            return this;
        }
        return null;
    }

    /**
     * Returns a formatted string representation of the deadline.
     *
     * Format: "[D][status] description (by: due date and time)"
     * where status is "[X]" if done or "[ ]" if not done.
     *
     * @return formatted string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishBy.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the storage format representation of the deadline.
     *
     * Format: "D | status | description | due date/time"
     * where status is "1" if done or "0" if not done.
     *
     * @return storage format string for persisting the deadline to file
     */
    @Override
    public String storeFormat() {
        return "D | " + this.getStoreStatus() + " | " + this.getDesc() + " | " + finishBy.format(INPUT_FORMAT);
    }
}
