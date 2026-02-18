package astrid;

public class TaskNotFoundException extends AstridException {

    public TaskNotFoundException() {
        super("The task you are looking for doesn't exist! Maybe type `list` to see all your tasks?");
    }
}
