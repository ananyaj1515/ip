package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int index) {
        Task curr = this.tasks.remove(index - 1);
        return curr;
    }

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