import java.util.ArrayList;
import java.util.Scanner;


public class AstridGlowspell {

    // instance variables
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    // static variables
    private static final String divider = "\t°. ݁₊ ⊹ . ݁ ⟡ ݁ . ⊹ ₊ ݁.\n";
    private static enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE
    }

    // greeting
    private void greet() {
        String greeting = "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up\n";
        System.out.print(AstridGlowspell.divider + greeting + AstridGlowspell.divider);
    }

    // farewell
    private void bye() {
        String farewell = "\tUntil our planets align again, may your transits be gentle\n";
        System.out.print(AstridGlowspell.divider + farewell + AstridGlowspell.divider);
    }

    // view all tasks
    private void list() {
        if (tasks.isEmpty()) {

            System.out.print(divider);
            System.out.println("\tYou have no tasks yet!");
            System.out.print(divider);
            return;
        }
        System.out.print(divider);
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("\t %d. %s\n", i + 1,tasks.get(i).toString());
        }
        System.out.print(divider);
    }

    // mark as done
    private void mark(int index) {
        try {
            if (index > this.tasks.size()) {
                throw new TaskNotFoundException();
            }
            tasks.get(index - 1).markAsDone();
            System.out.print(divider);
            System.out.println("\t Nice I've marked this task as done:");
            System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
            System.out.print(divider);
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
            tasks.get(index - 1).markAsUndone();
            System.out.print(divider);
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
            System.out.print(divider);
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
            System.out.print(divider);
            System.out.println("\t Noted. I've removed this task");
            System.out.printf("\t\t %s\n", removed);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
            System.out.print(divider);
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
            System.out.print(divider);
            System.out.println("\t Got it. I've added the task");
            System.out.printf("\t\t%s\n", curr);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
            System.out.print(divider);

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

            System.out.print(divider);
            System.out.println("\t Got it. I've added the task");
            System.out.printf("\t\t%s\n", curr);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
            System.out.print(divider);

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

            System.out.print(divider);
            System.out.println("\t Got it. I've added the task");
            System.out.printf("\t\t%s\n", curr);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
            System.out.print(divider);

        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // convert command to an enum for the case statement
    private Command getEnumCommand(String command) {
        String commandUpper= command.trim().toUpperCase();
        return Command.valueOf(commandUpper);
    }

    // helper function to run to manage inputted commands
    private void simulate() {
        while (sc.hasNextLine()) {
            try {
                if (!sc.hasNextLine()) {
                    return;
                }
                String input = this.sc.nextLine();
                String[] inputs = input.split(" ", 2);
                int index = 0;
                Command command = this.getEnumCommand(inputs[0]);
                if (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE) {
                    index = Integer.parseInt(inputs[1]);
                }

                switch(command) {
                    case LIST:
                        this.list();
                        break;
                    case MARK:
                        this.mark(index);
                        break;
                    case UNMARK:
                        this.unmark(index);
                        break;
                    case DELETE:
                        this.delete(index);
                        break;
                    case TODO:
                        this.toDo(inputs[1]);
                        break;
                    case DEADLINE:
                        this.deadline(inputs[1]);
                        break;
                    case EVENT:
                        this.event(inputs[1]);
                        break;
                    case BYE:
                        this.bye();
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
        chatbot.greet();
        chatbot.simulate();

    }
}
