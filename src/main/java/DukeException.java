public class DukeException extends Exception{

    Ui ui = new Ui();
    public DukeException() {
        super("Oh no, Something went wrong! Mercury is in retrograde today i guess");
    }

    public DukeException(String message) {
        super(message);
    }
}
