package duke.task;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns string representation of completion task of current task
     * @return String checkbox
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns string representation as 1 or 0 for writing to local storage
     * @return String status
     */
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

    /**
     * updates completion status of given task to done
     * @return void
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * updates completion status of given task to not done
     * @return void
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * generates string format of Task for writing to local file
     * @return String representation of Task for storage
     */
    public String storeFormat() {
        return "";
    }
}
