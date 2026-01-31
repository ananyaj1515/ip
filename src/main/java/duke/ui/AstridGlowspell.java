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

    /**
     * Prints list of current tasks
     */
    private void list() {
        if (tasks.isEmpty()) {
            ui.dividerWrap("You have no tasks yet!");
            return;
        }
        ui.dividerWrapNoTab(tasks.toString());
    }

    /**
     * Marks Task at given index as done
     *
     * @param index task number in list (1-indexed)
     */
    private void mark(int index) {
        try {
            if (index > this.tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index);
            task.markAsDone();
            ui.mark(task);
        } catch (TaskNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Marks Task at given index as not done
     *
     * @param index task number in list (1-indexed)
     */
    private void unmark(int index) {
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index);
            task.markAsUndone();
            ui.unmark(task);

        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void find(String keyword) {
        TaskList res = tasks.find(keyword);
        ui.find(res);
    }

    /**
     * Removes Task at given index from list
     *
     * @param index task number in list (1-indexed)
     */
    private void delete(int index) {
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task removed = tasks.remove(index);
            ui.delete(removed, tasks.size());
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates new ToDo task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description
     */
    private void toDo(String input) {
        try {
            if (input.isEmpty()) {
                throw new MissingArgumentException("Oh no! The description of todo cannot be empty!");
            }
            Task curr = new ToDo(input);
            tasks.add(curr);
            ui.toDo(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Creates new Deadline task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description, finishBy
     */
    private void deadline(String input) {
        try {
            String[] inputs = input.split("/by");
            if (inputs.length < 2) {
                throw new MissingArgumentException("Oh no! You forgot to enter the description or the deadline!");
            }
            Task curr = new Deadline(inputs[0].trim(), inputs[1].trim());
            tasks.add(curr);
            ui.deadline(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates new Deadline task, adds to list and prints confirmation to user
     *
     * @param input string containing Task description, from and to
     */
    private void event(String input) {
        try {
            String[] inputs = input.split("/to|/from");
            if (inputs.length < 3) {
                throw new MissingArgumentException(
                        "Oh no! You forgot to enter the description or the start or the end!");
            }
            Task curr = new Event(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
            tasks.add(curr);
            ui.event(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Simulates the chatbot by accepting user input
     * Parses the input to find corresponding command
     * Executes command
     */
    private void simulate() {
        while (inputScanner.hasNextLine()) {
            try {
                if (!inputScanner.hasNextLine()) {
                    return;
                }
                String input = this.inputScanner.nextLine();
                Command command = Parser.parseCommand(input);
                String argument = Parser.parseArguments(input);

                switch(command) {
                case LIST:
                    this.list();
                    break;
                case MARK:
                    this.mark(Parser.parseIndex(argument));
                    break;
                case UNMARK:
                    this.unmark(Parser.parseIndex(argument));
                    break;
                case DELETE:
                    this.delete(Parser.parseIndex(argument));
                    break;
                case FIND:
                    this.find(argument);
                    break;
                case TODO:
                    this.toDo(argument);
                    break;
                case DEADLINE:
                    this.deadline(argument);
                    break;
                case EVENT:
                    this.event(argument);
                    break;
                case BYE:
                    ui.bye();
                    try {
                        storage.saveToFile(tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                default:
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // get user input
        AstridGlowspell chatbot = new AstridGlowspell();
        chatbot.simulate();

    }
}
