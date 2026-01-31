package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    private LocalDateTime finishBy;

    public Deadline(String desc, String finishBy) {
        super(desc);
        this.finishBy = LocalDateTime.parse(finishBy, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishBy.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String storeFormat() {
        return "D | " + this.getStoreStatus() + " | " + this.getDesc() + " | " + finishBy.format(INPUT_FORMAT);
    }
}
