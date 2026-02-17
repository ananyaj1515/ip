package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds new task to TaskList
     *
     * @param task Task to add to list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns number of tasks in the list
     *
     * @return int size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes task from TaskList
     *
     * @param index task number in list (1 indexed)
     * @return Task just removed
     */
    public Task remove(int index) {
        Task curr = this.tasks.remove(index - 1);
        return curr;
    }

    /**
     * Gets task with specified index from TaskList
     *
     * @param index task number in list (1 indexed)
     * @return Task needed
     */
    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return true if the list contains no tasks, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Searches tasks for the given keyword and returns a TaskList containing matches.
     *
     * @param keyword substring to search for in tasks
     * @return TaskList containing tasks that match the keyword
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();
        for (Task curr: this.tasks) {
            if (curr.find(keyword) != null) {
                results.add(curr);
            }
        }
        return results;
    }

    /**
     * Collects tasks that are due or relevant for today and returns them in a TaskList.
     *
     * @return TaskList containing today's tasks
     */
    public TaskList remind() {
        TaskList today = new TaskList();
        for (Task curr : this.tasks) {
            if (curr.today() != null) {
                today.add(curr);
            }
        }
        System.out.println(today);
        return today;
    }

    /**
     * Returns a formatted string representation of the task list.
     *
     * @return formatted list of tasks with index numbers
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            String formatted = String.format("%d. %s\n", i, tasks.get(i - 1).toString());
            str.append(formatted);
        }
        return str.toString();
    }

    /**
     * Exposes the internal list of tasks.
     *
     * @return the underlying ArrayList of Task objects
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
