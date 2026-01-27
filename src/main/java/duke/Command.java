package duke;

public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE;

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



