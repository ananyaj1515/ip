import java.util.ArrayList;
import java.util.Scanner;


public class AstridGlowspell {
    public static void main(String[] args) {

        // declarations
        String divider = "\t°. ݁₊ ⊹ . ݁ ⟡ ݁ . ⊹ ₊ ݁.";
        String greeting = "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up";
        String farewell = "\tUntil our planets align again, may your transits be gentle";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // greetings
        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        // get user input
        String input = sc.nextLine();
        while (!(input.equals("bye"))) {

            // for aesthetic
            System.out.println(divider);

            // view all tasks
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t %d. %s\n", i + 1,tasks.get(i).toString());
                }
            }

            // mark task as done
            else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index > tasks.size()) {
                        throw new TaskNotFoundException();
                    }
                    tasks.get(index - 1).markAsDone();
                    System.out.println("\t Nice I've marked this task as done:");
                    System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
                } catch (TaskNotFoundException e) {
                    System.out.println(e.toString());
                }
            }

            // unmark task as done
            else if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index > tasks.size()) {
                        throw new TaskNotFoundException();
                    }
                    tasks.get(index - 1).markAsUndone();
                    System.out.println("\t OK, I've marked this task as not done yet:");
                    System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
                } catch (TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index > tasks.size()) {
                        throw new TaskNotFoundException();
                    }
                    Task removed = tasks.remove(index - 1);
                    System.out.println("\t Noted. I've removed this task");
                    System.out.printf("\t\t %s\n", removed);
                    System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
                } catch (TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            // commands for adding different tasks
            else {

                //add new ToDo Task
                if (input.startsWith("todo")){
                    try {
                        input = input.substring(4);
                        if (input.isEmpty()) {
                            throw new MissingArgumentException("Oh no! The description of todo cannot be empty!");
                        }
                        Task curr = new ToDo(input);
                        tasks.add(curr);
                        System.out.println("\t Got it. I've added the task");
                        System.out.printf("\t\t%s\n", curr);
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list");

                    } catch (MissingArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // add new Deadline Task
                else if (input.startsWith("deadline")){
                    try {
                        input = input.substring(8);
                        String[] inputs = input.split("/by");
                        if (inputs.length < 2) {
                            throw new MissingArgumentException("Oh no! You forgot to enter the description or the deadline!");
                        }
                        Task curr = new Deadline(inputs[0].trim(), inputs[1].trim());
                        tasks.add(curr);
                        System.out.println("\t Got it. I've added the task");
                        System.out.printf("\t\t%s\n", curr);
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list");

                    } catch (MissingArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                // add new event Task
                } else if (input.startsWith("event")) {
                    try {
                        input = input.substring(5);
                        String[] inputs = input.split("/to | /from");
                        if (inputs.length < 3) {
                            throw new MissingArgumentException("Oh no! You forgot to enter the description or the start or the end!");
                        }
                        Task curr = new Event(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
                        tasks.add(curr);
                        System.out.println("\t Got it. I've added the task");
                        System.out.printf("\t\t%s\n", curr);
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
                    } catch (MissingArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                // command doesn't exist
                } else {
                    try {
                        throw new UnknownCommandException();
                    } catch (UnknownCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // for aesthetic
            System.out.println(divider);

            // take next command
            input = sc.nextLine();
        }

        // farewell
        System.out.println(divider);
        System.out.println(farewell);
        System.out.println(divider);

        // closing the scanner
        sc.close();
    }
}
