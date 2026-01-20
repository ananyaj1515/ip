import java.util.ArrayList;
import java.util.Scanner;


public class AstridGlowspell {
    public static void main(String[] args) {
        String divider = "\t°. ݁₊ ⊹ . ݁ ⟡ ݁ . ⊹ ₊ ݁.";
        String greeting = "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up";
        String farewell = "\tUntil our planets align again, may your transits be gentle";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        String input = sc.nextLine();
        while (!(input.equals("bye"))) {
            System.out.println(divider);
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t %d. %s\n", i + 1,tasks.get(i));
                }
            }
            else {
                tasks.add(input);
                System.out.println("\t added: " + input);
            }

            System.out.println(divider);
            input = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println(farewell);
        System.out.println(divider);
        sc.close();
    }
}
