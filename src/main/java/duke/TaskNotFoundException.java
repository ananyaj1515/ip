package duke;

public class TaskNotFoundException extends DukeException {

    public TaskNotFoundException() {
        super("The task you are looking for doesn't exist :((");
    }
}
