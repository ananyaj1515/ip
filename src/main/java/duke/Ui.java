package duke;

import duke.task.Task;

public class Ui {
    /**
     * Greets user
     */
    public String greet() {
        return "\tHi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up\n";
    }

    /**
     * Displays exit message
     */
    public String bye() {
        return "Until our planets align again, may your transits be gentle\n";
    }

    /**
     * Updates Task status to undone
     *
     * @param task Task to be marked as incomplete
     */
    public String unmark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\t OK, I've marked this task as not done yet:\n");
        str.append(String.format("\t\t %s\n", task.toString()));
        return str.toString();
    }

    /**
     * Updates Task status to undone
     *
     * @param task Task to be marked as incomplete
     */
    public String mark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\t Nice I've marked this task as done:\n");
        str.append(String.format("\t\t %s\n", task.toString()));
        return str.toString();
    }

    /**
     * Updates Task status to undone
     *
     * @param removed Task that was just removed
     * @param left number of task items left
     *
     */
    public String delete(Task removed, int left) {
        StringBuilder str = new StringBuilder();
        str.append("\t Noted. I've removed this task\n");
        str.append(String.format("\t\t %s\n", removed));
        str.append(String.format("\t Now you have " + left + " tasks in the list\n"));
        return str.toString();
    }

    public String find(TaskList results) {
        StringBuilder str = new StringBuilder();
        str.append("\t Here are the matching results from your list \n");
        str.append(results.toString());
        return str.toString();
    }

    public String remind(TaskList today) {
        StringBuilder str = new StringBuilder();
        str.append("\t Here are the tasks due today \n");
        str.append(today.toString());
        return str.toString();
    }

    /**
     * Displays success message for adding new ToDo Task
     *
     * @param added Task that was just added
     * @param size number of task items now
     *
     */
    public String toDo(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }

    /**
     * Displays success message for adding new Deadline Task
     *
     * @param added Task that was just added
     * @param size number of task items now
     *
     */
    public String deadline(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }

    /**
     * Displays success message for adding new Event Task
     *
     * @param added Task that was just added
     * @param size number of task items now
     *
     */
    public String event(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("\t Got it. I've added the task\n");
        str.append(String.format("\t\t%s\n", added));
        str.append(String.format("\t Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }
}
