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
            System.out.println(divider);

            // view all tasks
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t %d. %s\n", i + 1,tasks.get(i).toString());
                }
            }

            // mark task as done
            else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("\t Nice I've marked this task as done:");
                System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
            }

            // unmark task as done
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks.get(index - 1).markAsUndone();
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
            }

            else {

                System.out.println("\t Got it. I've added the task");
                if (input.startsWith("todo")){
                    input = input.substring(5);
                    Task curr = new ToDo(input);
                    tasks.add(curr);
                    System.out.printf("\t\t%s\n", curr.toString());
                }

                // add new Deadline Task
                else if (input.startsWith("deadline")){
                    input = input.substring(9);
                    String[] inputs = input.split("/by");
                    Task curr = new Deadline(inputs[0].trim(), inputs[1].trim());
                    tasks.add(curr);
                    System.out.printf("\t\t%s\n", curr.toString());

                } else {
                    input = input.substring(6);
                    String[] inputs = input.split("/to | /from");
                    Task curr = new Event(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
                    tasks.add(curr);
                    System.out.printf("\t\t%s\n", curr.toString());
                }
                System.out.println("\t Now you have " + tasks.size() + " tasks in the list");
            }

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
