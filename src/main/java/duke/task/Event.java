package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    public LocalDateTime start;
    public LocalDateTime end;

    public Event(String desc, String start, String end) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT)+ " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String storeFormat() {
        return "E | " + this.getStoreStatus() + " | " + this.getDesc() + " | " + start.format(INPUT_FORMAT) + "-" + end.format(INPUT_FORMAT);
    }
}
