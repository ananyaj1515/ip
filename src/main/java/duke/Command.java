package duke;

public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE;

    /**
     * cleans user command and finds corresponding enum Command type
     * @param command as uncleaned inputted string
     * @throws IllegalArgumentException when invalid command inputted
     * @return Command corresponding to user input
     */
    public static Command getEnumCommand(String command) throws UnknownCommandException {
        String commandUpper= command.trim().toUpperCase();
        Command eCommand;
        try {
            eCommand =  Command.valueOf(commandUpper);
        }
        // backup case when the command doesn't exist
        catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        return eCommand;
    }
}



