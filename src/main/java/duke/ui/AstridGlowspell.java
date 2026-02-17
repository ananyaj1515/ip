package duke.ui;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.console;
import static java.lang.System.exit;

import duke.Command;
import duke.DukeException;
import duke.MissingArgumentException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.TaskNotFoundException;
import duke.Ui;
import duke.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * AstridGlowspell is the UI/controller class that wires together parsing,
 * task storage, task list management, and the user interface.
 */
public class AstridGlowspell {

    // instance variables
    private Scanner inputScanner = new Scanner(System.in);
    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage("./data/AstridGlowspell.txt");

    /**
     * Constructs a new AstridGlowspell instance, loads stored tasks and
     * displays the greeting via the Ui.
     */
    public AstridGlowspell() {
        storage.loadStoredTasks(tasks);
    }

    /**
     * Returns the chatbot's response for the given raw input string.
     *
     * @param input raw user input
     * @return response string to be shown to the user
     */
    public String getResponse(String input) {
        return simulate(input);
    }

    public String greet() {
        return ui.greet();
    }

    /**
     * Returns a string representation of the current task list or a message
     * indicating the list is empty.
     *
     * @return list of tasks formatted for display or empty message
     */
    private String list() {
        if (tasks.isEmpty()) {
//            ui.dividerWrap("You have no tasks yet!");
            return "You have no tasks yet!";
        }
        return ui.list(tasks);
    }

    /**
     * Marks the task at the given 1-indexed position as done and returns
     * the confirmation message.
     *
     * @param index task number in list (1-indexed)
     * @return confirmation message or error message if task not found
     */
    private String mark(int index) {
        assert index > 0;
        try {
            if (index > this.tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index);
            task.markAsDone();
            return ui.mark(task);
        } catch (TaskNotFoundException e) {
            return(e.getMessage());
        }
    }

    /**
     * Marks the task at the given 1-indexed position as not done and returns
     * the confirmation message.
     *
     * @param index task number in list (1-indexed)
     * @return confirmation message or error message if task not found
     */
    private String unmark(int index) {
        assert index > 0;
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index);
            task.markAsUndone();
            return ui.unmark(task);

        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
            return (e.getMessage());
        }
    }

    /**
     * Finds tasks that match the given keyword and returns the formatted
     * find results via the Ui.
     *
     * @param keyword substring to search for in task descriptions
     * @return formatted find results
     */
    private String find(String keyword) {
        assert !keyword.isEmpty();
        TaskList res = tasks.find(keyword);
        if (res.isEmpty()) {
            return "No matching tasks found!";
        }
        return ui.find(res);
    }

    /**
     * Removes the task at the given 1-indexed position from the list and
     * returns the confirmation message.
     *
     * @param index task number in list (1-indexed)
     * @return confirmation message or error message if task not found
     */
    private String delete(int index) {
        assert index > 0;
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task removed = tasks.remove(index);
            return ui.delete(removed, tasks.size());
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
            return (e.getMessage());
        }
    }

    /**
     * Collects tasks that are due or relevant for today and returns the
     * reminder output from the Ui.
     *
     * @return formatted reminder information for today's tasks
     */
    private String today() {
        TaskList today = tasks.remind();
        if (today.isEmpty()) {
            return "You have no tasks due today!";
        }
        return ui.remind(today);
    }

    /**
     * Creates a new ToDo task from the given input, adds it to the list and
     * returns the confirmation message.
     *
     * @param input description for the ToDo task
     * @return confirmation message or error message if description missing
     */
    private String toDo(String input) {
        try {
            if (input.isEmpty()) {
                throw new MissingArgumentException("Oh no! The description of todo cannot be empty!");
            }
            Task curr = new ToDo(input);
            tasks.add(curr);
            return ui.toDo(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Creates a new Deadline task from the given input (description and /by),
     * adds it to the list and returns the confirmation message.
     *
     * @param input string containing Task description and \"/by\" deadline
     * @return confirmation message or error message if arguments missing
     */
    private String deadline(String input) {
        int numArguments = 2;
        try {
            String[] inputs = input.split("/by");
            if (inputs.length < numArguments) {
                throw new MissingArgumentException("Oh no! You forgot to enter the description or the deadline!");
            }
            Task curr = new Deadline(inputs[0].trim(), inputs[1].trim());
            tasks.add(curr);
            return ui.deadline(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Creates a new Event task from the given input (description, /from, /to),
     * adds it to the list and returns the confirmation message.
     *
     * @param input string containing Task description, \"/from\" start and \"/to\" end
     * @return confirmation message or error message if arguments missing
     */
    private String event(String input) {
        int numArguments = 3;
        try {
            String[] inputs = input.split("/to|/from");
            if (inputs.length < numArguments) {
                throw new MissingArgumentException(
                        "Oh no! You forgot to enter the description or the start or the end!");
            }
            Task curr = new Event(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
            tasks.add(curr);
            return ui.event(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Parses and executes a single command input, delegating to the proper
     * handler and returning the result string. On BYE the tasks will be saved
     * and the application will attempt to exit.
     *
     * @param input raw user input
     * @return output string resulting from executing the command, or error message
     */
    private String simulate(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String argument = Parser.parseArguments(input);

            switch (command) {
                case LIST: return list();
                case MARK: return mark(Parser.parseIndex(argument));
                case UNMARK: return unmark(Parser.parseIndex(argument));
                case DELETE: return delete(Parser.parseIndex(argument));
                case FIND: return find(argument);
                case TODO: return toDo(argument);
                case DEADLINE: return deadline(argument);
                case EVENT: return event(argument);
                case REMIND: return today();
                case BYE:
                    storage.saveToFile(tasks);
                    return ui.bye();
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
