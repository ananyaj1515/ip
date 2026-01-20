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

            // add new task
            else  {
                tasks.add(new Task(input));
                System.out.println("\t added: " + input);
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
