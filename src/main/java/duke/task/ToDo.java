package duke.task;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeFormat() {
        return "T | " + this.getStoreStatus() + " | " + this.getDesc();
    }

    @Override
    public Task today() {
        return null;
    }
}
