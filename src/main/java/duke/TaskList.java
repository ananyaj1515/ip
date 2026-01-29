package duke;

import duke.task.Task;

import java.util.ArrayList;

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

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            String formatted = String.format("\t %d. %s\n", i,tasks.get(i - 1).toString());
            str.append(formatted);
        }
        return str.toString();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}