public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }


    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getStoreStatus() {
        return isDone ? "1" : "0";
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.desc;
    }

    public void markAsDone() {
        this.isDone = true;
    }


    public void markAsUndone() {
        this.isDone = false;
    }

    public String storeFormat() {
        return "";
    }
}
