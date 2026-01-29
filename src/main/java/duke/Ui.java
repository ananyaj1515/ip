package duke;

import duke.task.Task;

public class Ui {

    private static final String divider = "\t°. ݁₊ ⊹ . ݁ ⟡ ݁ . ⊹ ₊ ݁.\n";

    public void dividerWrap(String text) {
        System.out.println(divider);
        System.out.println('\t' + text);
        System.out.print(divider);
    }

    public void dividerWrapNoTab(String text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.print(divider);
    }

    public void dividerWrap(StringBuilder text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.print(divider);
    }

    public void greet() {
        String greeting = "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up\n";
        System.out.print(divider + greeting + divider);
    }

    public void bye() {
        String farewell = "\tUntil our planets align again, may your transits be gentle\n";
        System.out.print(divider + farewell + divider);
    }

    public void unmark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\t OK, I've marked this task as not done yet:\n");
        str.append(String.format("\t\t %s\n", task.toString()));
        this.dividerWrap(str);
    }

    public void mark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\t Nice I've marked this task as done:\n");
        str.append(String.format("\t\t %s\n", task.toString()));
        this.dividerWrap(str);
    }

    public void delete(Task removed, int left) {
        StringBuilder str = new StringBuilder();
        str.append("\t Noted. I've removed this task\n");
        str.append(String.format("\t\t %s\n", removed));
        str.append(String.format("\t Now you have " + left + " tasks in the list\n"));
        this.dividerWrap(str);
    }

    public void toDo(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        this.dividerWrap(str);
    }

    public void deadline(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        this.dividerWrap(str);
    }

    public void event(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        this.dividerWrap(str);
    }
}
