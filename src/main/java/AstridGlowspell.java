import java.util.Scanner;


public class AstridGlowspell {
    public static void main(String[] args) {
        String divider = "\t°. ݁₊ ⊹ . ݁ ⟡ ݁ . ⊹ ₊ ݁.";
        String greeting = "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up";
        String farewell = "\tUntil our planets align again, may your transits be gentle";
        Scanner sc = new Scanner(System.in);

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        String input = sc.nextLine();
        while (!(input.equals("bye"))) {
            System.out.println(divider);
            System.out.println("\t" + input);
            System.out.println(divider);
            input = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println(farewell);
        System.out.println(divider);
        sc.close();
    }
}
