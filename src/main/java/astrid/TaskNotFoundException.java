package astrid;

/**
 * Exception thrown when a task is not found in the task list.
 *
 * TaskNotFoundException extends AstridException and is raised when the user
 * attempts to access, modify, or delete a task that doesn't exist in the task list.
 * It provides a default error message suggesting the user check the task list.
 */
public class TaskNotFoundException extends AstridException {

    /**
     * Constructs a TaskNotFoundException with a default error message.
     *
     * The default message informs the user that the task they're looking for
     * doesn't exist and helpfully suggests using the 'list' command to view
     * all available tasks.
     */
    public TaskNotFoundException() {
        super("The task you are looking for doesn't exist! Maybe type `list` to see all your tasks?");
    }
}
