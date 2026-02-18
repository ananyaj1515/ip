package astrid.task;

/**
 * Abstract base class representing a task with a description and completion status.
 *
 * Task is an abstract class that serves as the parent class for all task types
 * (ToDo, Deadline, Event). It maintains task information such as description and
 * whether the task is complete or not. Concrete subclasses must implement the
 * abstract methods for task-specific behavior.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially marked as not done.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns string representation of completion status of the current task.
     *
     * @return "[X]" if the task is done, "[ ]" if the task is not done
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns string representation as 1 or 0 for writing to local storage.
     *
     * @return "1" if the task is done, "0" if the task is not done
     */
    public String getStoreStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Gets the description of the task.
     *
     * @return the task description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns a string representation of the task including its completion status and description.
     *
     * Format: "[status] description"
     * where status is "[X]" if done or "[ ]" if not done.
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatus() + " " + this.desc;
    }

    /**
     * Updates the completion status of the task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates the completion status of the task to not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Searches for the given keyword in the task description.
     *
     * @param keyword the search keyword to find in the description
     * @return this Task if the keyword is found in the description, null otherwise
     */
    public Task find(String keyword) {
        return this.desc.contains(keyword) ? this : null;
    }

    /**
     * Checks if the task is relevant or due for today.
     *
     * This abstract method must be implemented by subclasses to provide task-specific
     * logic for determining if the task is due today (e.g., checking dates for Deadline
     * and Event tasks).
     *
     * @return the Task if it is due today, null otherwise
     */
    public abstract Task today();

    /**
     * Generates the storage format representation of the task for writing to a local file.
     *
     * Subclasses must override this method to provide their specific storage format.
     * This allows tasks to be persisted and reloaded from file storage.
     *
     * @return string representation of the task in storage format
     */
    public String storeFormat() {
        return "";
    }
}
