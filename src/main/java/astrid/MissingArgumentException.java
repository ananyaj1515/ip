package astrid;

/**
 * Exception thrown when a command is missing required arguments.
 * MissingArgumentException extends AstridException and is raised when the user
 * provides a command without the necessary arguments. It provides a default error
 * message but also allows for custom error messages.
 */
public class MissingArgumentException extends AstridException {

    /**
     * Constructs a MissingArgumentException with a default error message.
     * The default message informs the user that they are missing an argument
     * and should try the command again.
     */
    public MissingArgumentException() {
        super("You are missing an argument in this command! Try again");
    }

    /**
     * Constructs a MissingArgumentException with a specified error message.
     *
     * @param message the detail message explaining what argument is missing
     */
    public MissingArgumentException(String message) {
        super(message);
    }

}
