package duke;

public class Parser {
    public static Command parseCommand(String input)  {
        String command = input.trim().split(" ")[0].toUpperCase();
        try {
            return Command.getEnumCommand(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String parseArguments(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length < 2 ? "" : parts[1].trim();
    }

    public static int parseIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new DukeException("Index must be a number");
        }
    }

}

