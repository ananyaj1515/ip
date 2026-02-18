package astrid;

public class UnknownCommandException extends AstridException {

    public UnknownCommandException() {
        super("The command you entered doesn't exist! Sorry!");
    }
}
