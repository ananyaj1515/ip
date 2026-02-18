package astrid;

/**
 * AstridException is a custom exception class that represents errors and exceptional
 * conditions within the AstridGlowspell application.
 *
 * It provides a default error message for generic exceptions and allows for custom
 * error messages to be passed to the constructor.
 */
public class AstridException extends Exception {

    /**
     * Constructs an AstridException with a default error message.
     */
    public AstridException() {
        super("Oh no, Something went wrong! Mercury is in retrograde today i guess");
    }

    /**
     * Constructs an AstridException with a specified error message.
     *
     * @param message the detail message explaining the exception
     */
    public AstridException(String message) {
        super(message);
    }
}
