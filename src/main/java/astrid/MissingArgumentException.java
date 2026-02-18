package astrid;

public class MissingArgumentException extends AstridException {

    public MissingArgumentException() {
        super("You are missing an argument in this command! Try again");
    }

    public MissingArgumentException(String message) {
        super(message);
    }

}
