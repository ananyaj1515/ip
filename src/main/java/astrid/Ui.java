package astrid;

import astrid.task.Task;

/**
 * Ui class handles all user interface messages and formatting for the AstridGlowspell chatbot.
 *
 * It provides methods to display various messages including greetings, task lists, confirmations
 * for task operations (add, delete, mark, unmark), and search results. All messages are themed
 * around an astrological/mystical persona.
 */
public class Ui {
    /**
     * Greets the user with a welcome message.
     *
     * @return a greeting message from the chatbot
     */
    public String greet() {
        return "Hi Starlight, I am Astrid Glowspell! The planets were gossiping and your name came up\n";
    }

    /**
     * Displays the entire task list to the user.
     *
     * @param list TaskList to be displayed
     * @return formatted string representation of the task list
     */
    public String list(TaskList list) {
        return "Your cosmic ledger, Starlight \n" + list.toString();
    }

    /**
     * Displays an exit message when the user says goodbye.
     *
     * @return farewell message from the chatbot
     */
    public String bye() {

        return "Until our planets align again, may your transits be gentle\n";
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task Task to be marked as incomplete
     * @return confirmation message with the unmarked task
     */
    public String unmark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("Fate delayed… interesting.\n I've marked this task as not done yet:\n");
        str.append(String.format("\t%s\n", task.toString()));
        return str.toString();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task Task to be marked as complete
     * @return confirmation message with the marked task
     */
    public String mark(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("Destiny fulfilled. Saturn approves. \n I've marked this task as done:\n");
        str.append(String.format("\t%s\n", task.toString()));
        return str.toString();
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param removed Task that was just removed
     * @param left number of task items remaining in the list
     * @return confirmation message with the deleted task and remaining count
     */
    public String delete(Task removed, int left) {
        StringBuilder str = new StringBuilder();
        str.append("Erased from the cosmic scroll. \nI've removed this task\n");
        str.append(String.format("\t%s\n", removed));
        str.append(String.format("Now you have " + left + " tasks in the list\n"));
        return str.toString();
    }

    /**
     * Displays search results for tasks matching a keyword.
     *
     * @param results TaskList containing tasks that match the search keyword
     * @return formatted string representation of the search results
     */
    public String find(TaskList results) {
        StringBuilder str = new StringBuilder();
        str.append("The stars revealed this: \n");
        str.append(results.toString());
        return str.toString();
    }

    /**
     * Displays tasks that are due or relevant for today.
     *
     * @param today TaskList containing tasks due today
     * @return formatted string representation of today's tasks
     */
    public String remind(TaskList today) {
        StringBuilder str = new StringBuilder();
        str.append("The moon requests your attention today for: \n");
        str.append(today.toString());
        return str.toString();
    }

    /**
     * Displays a success message when a new ToDo task is added.
     *
     * @param added Task that was just added
     * @param size total number of tasks in the list after addition
     * @return confirmation message with the added task and new total count
     */
    public String toDo(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("A new intention enters the stars.\n I've added the task\n");
        str.append(String.format("\t%s\n", added));
        str.append(String.format("Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }

    /**
     * Displays a success message when a new Deadline task is added.
     *
     * @param added Task that was just added
     * @param size total number of tasks in the list after addition
     * @return confirmation message with the added task and new total count
     */
    public String deadline(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("A fated alignment has been marked.\n I've added the task\n");
        str.append(String.format("\t%s\n", added));
        str.append(String.format("Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }

    /**
     * Displays a success message when a new Event task is added.
     *
     * @param added Task that was just added
     * @param size total number of tasks in the list after addition
     * @return confirmation message with the added task and new total count
     */
    public String event(Task added, int size) {
        StringBuilder str = new StringBuilder();
        str.append("A cosmic affair begins and ends in my charts.\n I've added the task\n");
        str.append(String.format("\t%s\n", added));
        str.append(String.format("Now you have " + size + " tasks in the list\n"));
        return str.toString();
    }
}
