import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AstridGlowspell {

    // instance variables
    private final Scanner inputScanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
    Ui ui = new Ui();
    Storage storage = new Storage("data/AstridGlowspell.txt");

    public AstridGlowspell() {
        storage.loadStoredTasks(tasks);
        ui.greet();
    }

    // static variables

    // view all tasks
    private void list() {
        if (tasks.isEmpty()) {
            ui.dividerWrap("You have no tasks yet!");
            return;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String formatted = String.format("\t %d. %s\n", i + 1,tasks.get(i).toString());
            str.append(formatted);
        }
        ui.dividerWrap(str);
    }

    // mark as done
    private void mark(int index) {
        try {
            if (index > this.tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index - 1);
            task.markAsDone();
            ui.mark(task);
        } catch (TaskNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    // unmark as done
    private void unmark(int index) {
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task task = tasks.get(index - 1);
            task.markAsUndone();
            ui.unmark(task);

        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // delete task
    private void delete(int index) {
        try {
            if (index > tasks.size()) {
                throw new TaskNotFoundException();
            }
            Task removed = tasks.remove(index - 1);
            ui.delete(removed, tasks.size());
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // add new todo
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

    // add new deadline
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

    // add new event
    private void event(String input) {
        try {
            String[] inputs = input.split("/to | /from");
            if (inputs.length < 3) {
                throw new MissingArgumentException("Oh no! You forgot to enter the description or the start or the end!");
            }
            Task curr = new Event(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
            tasks.add(curr);
            ui.event(curr, tasks.size());

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // helper function to run to manage inputted commands
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
        this.simulate();
    }

    public static void main(String[] args) {
        // get user input
        AstridGlowspell chatbot = new AstridGlowspell();
        chatbot.simulate();

    }
}
