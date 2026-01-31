package duke;

public class DukeException extends Exception {

    public DukeException() {
        super("Oh no, Something went wrong! Mercury is in retrograde today i guess");
    }

    public DukeException(String message) {
        super(message);
    }
}
