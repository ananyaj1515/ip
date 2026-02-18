package astrid.task;

/**
 * Represents a simple todo task with only a description.
 *
 * A ToDo extends Task and represents a basic task without any associated date or time.
 * It is the simplest form of a task, tracking only whether the task is complete or not.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     * The task is initially marked as not done.
     *
     * @param desc the description of the todo task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a formatted string representation of the todo task.
     *
     * Format: "[T][status] description"
     * where status is "[X]" if done or "[ ]" if not done.
     *
     * @return formatted string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the storage format representation of the todo task.
     *
     * Format: "T | status | description"
     * where status is "1" if done or "0" if not done.
     *
     * @return storage format string for persisting the todo task to file
     */
    @Override
    public String storeFormat() {
        return "T | " + this.getStoreStatus() + " | " + this.getDesc();
    }

    /**
     * Checks if the todo task is due today.
     *
     * Since a ToDo task has no associated date, it is never considered due today.
     * This method always returns null.
     *
     * @return always null, as todo tasks have no specific date
     */
    @Override
    public Task today() {
        return null;
    }
}
