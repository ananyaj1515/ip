package duke.ui;

import java.io.IOException;
import java.util.Scanner;

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


public class AstridGlowspell {

    // instance variables
    private Scanner inputScanner = new Scanner(System.in);
    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage("./data/AstridGlowspell.txt");

    public AstridGlowspell() {
        storage.loadStoredTasks(tasks);
        ui.greet();
    }

    public String getResponse(String input) {
        return simulate(input);
    }

    /**
     * Prints list of current tasks
     */
    private String list() {
        if (tasks.isEmpty()) {
//            ui.dividerWrap("You have no tasks yet!");
            return "You have no tasks yet!";
        }
        return tasks.toString();
    }

    /**
     * Marks Task at given index as done
     *
     * @param index task number in list (1-indexed)
     */
    private String mark(int index) {
        try {
            if (index > this.tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index);
            task.markAsDone();
            return ui.mark(task);
        } catch (TaskNotFoundException e) {
            System.out.println(e.toString());
            return(e.toString());
        }
    }

    /**
     * Marks Task at given index as not done
     *
     * @param index task number in list (1-indexed)
     */
    private String unmark(int index) {
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

    private String find(String keyword) {
        TaskList res = tasks.find(keyword);
        return ui.find(res);
    }

    /**
     * Removes Task at given index from list
     *
     * @param index task number in list (1-indexed)
     */
    private String delete(int index) {
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
     * Creates new ToDo task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description
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
     * Creates new Deadline task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description, finishBy
     */
    private String deadline(String input) {
        try {
            String[] inputs = input.split("/by");
            if (inputs.length < 2) {
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
     * Creates new Deadline task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description, from and to
     */
    private String event(String input) {
        try {
            String[] inputs = input.split("/to|/from");
            if (inputs.length < 3) {
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
     * Simulates the chatbot by accepting user input
     * Parses the input to find corresponding command
     * Executes command
     */
    private String simulate(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String argument = Parser.parseArguments(input);

            switch(command) {
            case LIST:
                return this.list();
            case MARK:
                return this.mark(Parser.parseIndex(argument));
            case UNMARK:
                return this.unmark(Parser.parseIndex(argument));
            case DELETE:
                return this.delete(Parser.parseIndex(argument));
            case FIND:
                return this.find(argument);
            case TODO:
                return this.toDo(argument);
            case DEADLINE:
                return this.deadline(argument);
            case EVENT:
                return this.event(argument);
            case BYE:

                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                return ui.bye();
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
