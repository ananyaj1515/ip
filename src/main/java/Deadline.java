public class Deadline extends Task {

    String finishBy;

    public Deadline(String desc, String finishBy) {
        super(desc);
        this.finishBy = finishBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishBy + ")";
    }
}
