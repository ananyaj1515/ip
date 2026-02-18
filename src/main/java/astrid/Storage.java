package astrid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import astrid.task.Deadline;
import astrid.task.Event;
import astrid.task.Task;
import astrid.task.ToDo;

/**
 * Handles persistence of tasks to a file and loading tasks from that file.
 *
 * The storage expects each line in the file to follow the store format produced
 * by Task.storeFormat\(\). On construction the file is created if it does not
 * exist and a Scanner is prepared for reading stored lines.
 */
public class Storage {
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String EVENT_IDENTIFIER = "E";
    private static final String TODO_IDENTIFIER = "T";

    private File file;
    private FileWriter fw;
    private Scanner fileScanner;

    /**
     * Constructs a Storage instance for the provided path.
     *
     * This will create any missing parent directories and the file itself if
     * they do not already exist, and prepares a Scanner to read the file.
     *
     * @param path path to the storage file
     * @throws AssertionError if {@code path} is empty
     */
    public Storage(String path) {
        assert !path.isEmpty();
        this.file = new File(path);

        try {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            this.fileScanner = new Scanner(this.file);
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }
    }

    /**
     * Writes the provided TaskList to the configured storage file.
     *
     * Each task is written in its {@code storeFormat()} representation followed
     * by a newline. The FileWriter used is overwritten each call.
     *
     * @param tasks task list to persist
     * @throws IOException if an I/O error occurs while writing the file
     */
    public void saveToFile(TaskList tasks) throws IOException {
        this.fw = new FileWriter(this.file);
        for (Task curr : tasks.getList()) {
            this.fw.write(curr.storeFormat() + "\n");
        }
        this.fw.close();
    }

    /**
     * Loads tasks from the storage file into the provided TaskList.
     *
     * The method expects lines in the format produced by the tasks' storeFormat.
     * Recognised identifiers are:
     * - {@code TODO_IDENTIFIER} for ToDo
     * - {@code DEADLINE_IDENTIFIER} for Deadline
     * - {@code EVENT_IDENTIFIER} for Event
     *
     * Lines are split on `|` or `-` separators with optional surrounding whitespace.
     * If a stored task is marked (\"1\"), it will be marked as done after creation.
     *
     * @param tasks TaskList to populate with loaded tasks
     */
    public void loadStoredTasks(TaskList tasks) {
        while (fileScanner.hasNextLine()) {
            String curr = this.fileScanner.nextLine();
            String[] params = curr.split("\\s*[|-]\\s*");
            Task task;
            switch (params[0]) {
            case TODO_IDENTIFIER:
                task = new ToDo(params[2]);
                if ("1".equals(params[1])) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case DEADLINE_IDENTIFIER:
                task = new Deadline(params[2], params[3]);
                if ("1".equals(params[1])) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case EVENT_IDENTIFIER:
                task = new Event(params[2], params[3], params[4]);
                if ("1".equals(params[1])) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            default:
                System.out.println("error");
            }
        }
    }
}
