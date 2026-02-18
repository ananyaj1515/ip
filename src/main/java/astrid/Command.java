package astrid;

/**
 * Enum representing the different commands that can be executed by the AstridGlowspell chatbot.
 *
 * The available commands are:
 * - LIST: Display all tasks
 * - MARK: Mark a task as done
 * - UNMARK: Mark a task as not done
 * - DELETE: Delete a task
 * - TODO: Add a new ToDo task
 * - DEADLINE: Add a new Deadline task
 * - EVENT: Add a new Event task
 * - BYE: Exit the application
 * - FIND: Search for tasks by keyword
 * - REMIND: Get tasks due today
 */
public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE, FIND, REMIND;

    /**
     * Converts a user input string to the corresponding Command enum value.
     *
     * The input string is trimmed and converted to uppercase before matching against
     * the enum values. This allows for case-insensitive command recognition.
     *
     * @param command the uncleaned user input string representing the command
     * @return the Command enum value corresponding to the input
     * @throws UnknownCommandException if the input does not match any valid command
     */
    public static Command getEnumCommand(String command) throws UnknownCommandException {
        String commandUpper = command.trim().toUpperCase();
        Command eCommand;
        try {
            eCommand = Command.valueOf(commandUpper);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        return eCommand;
    }
}
