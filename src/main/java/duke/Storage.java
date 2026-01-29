package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;
    private FileWriter fw;
    private Scanner fileScanner;

    public Storage(String path) {
        this.file = new File(path);
        if (!file.exists()) {
            return;
        }
        try {
            this.fileScanner = new Scanner(this.file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveToFile(TaskList tasks) throws IOException {
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        this.fw = new FileWriter(this.file);
        for (Task curr : tasks.getList()) {
            this.fw.write(curr.storeFormat() + "\n" );
        }
        this.fw.close();
    }

    public void loadStoredTasks(TaskList tasks) {
        while (fileScanner.hasNextLine()) {
            String curr = this.fileScanner.nextLine();
            String[] params = curr.split("\\s*[|-]\\s*");
            Task task;
            switch (params[0]){
                case "T":
                    task = new ToDo(params[2]);
                    if (params[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(params[2], params[3]);
                    if (params[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(params[2], params[3], params[4]);
                    if (params[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
            }

        }
    }
}
