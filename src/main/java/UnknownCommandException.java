public class UnknownCommandException extends DukeException{

    public UnknownCommandException() {
        super("The command you entered doesn't exist! Sorry!");
    }
}
