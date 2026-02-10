package duke;

public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE, FIND, REMIND;

    /**
     * cleans user command and finds corresponding enum Command type
     * @param command as uncleaned inputted string
     * @return Command corresponding to user input
     * @throws IllegalArgumentException when invalid command inputted
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



