package duke;

/**
 * Utility class for parsing user input into command parts.
 *
 * Provides helpers to extract the command word, the argument portion,
 * and to parse a numeric index from a string.
 */
public class Parser {

    /**
     * Parses the command word from the raw input string and returns
     * the corresponding {@code Command} enum value.
     *
     * @param input raw user input (must not be empty)
     * @return the matched {@code Command} enum, or {@code null} if parsing failed
     * @throws AssertionError if {@code input} is empty
     */
    public static Command parseCommand(String input) throws UnknownCommandException {
        assert !input.isEmpty();
        String command = input.trim().split(" ")[0].toUpperCase();
        try {
            return Command.getEnumCommand(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Extracts and returns the argument portion of the input string.
     *
     * If the input contains no arguments (only a single word), an empty
     * string is returned.
     *
     * @param input raw user input (must not be empty)
     * @return the argument string (trimmed), or an empty string if none
     * @throws AssertionError if {@code input} is empty
     */
    public static String parseArguments(String input) {
        assert !input.isEmpty();
        String[] parts = input.split(" ", 2);
        return parts.length < 2 ? "" : parts[1].trim();
    }

    /**
     * Parses an integer index from the provided string.
     *
     * The method attempts to convert the entire {@code input} to an integer.
     * If the conversion fails, a {@link DukeException} is thrown with a
     * descriptive message.
     *
     * @param input string representation of the index
     * @return the parsed integer index
     * @throws DukeException if {@code input} cannot be parsed as an integer
     */
    public static int parseIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Index must be a number");
        }
    }

}
