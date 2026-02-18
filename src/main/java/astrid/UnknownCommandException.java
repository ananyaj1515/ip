package astrid;

/**
 * Exception thrown when the user enters an unknown or invalid command.
 *
 * UnknownCommandException extends AstridException and is raised when the user
 * provides a command that is not recognized by the chatbot. It provides a default
 * error message informing the user that their command doesn't exist.
 */
public class UnknownCommandException extends AstridException {

    /**
     * Constructs an UnknownCommandException with a default error message.
     *
     * The default message informs the user that the command they entered
     * doesn't exist and apologizes for the inconvenience.
     */
    public UnknownCommandException() {
        super("The command you entered doesn't exist! Sorry!");
    }
}
