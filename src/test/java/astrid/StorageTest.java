package astrid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import astrid.task.Deadline;
import astrid.task.ToDo;


public class StorageTest {

    /**
     * Tests that the storage correctly saves tasks to a file and can load them back.
     * Creates a temporary file, saves two tasks (a ToDo and a Deadline), then verifies
     * that loading the file produces identical tasks.
     *
     * @throws IOException if there are file I/O errors during the test.
     */
    @Test
    public void saveToFile_writesCorrectly() throws IOException {
        File temp = new File("data/test.txt");
        Storage storage = new Storage(temp.getPath());

        TaskList tasks = new TaskList();
        tasks.add(new ToDo("return book"));
        tasks.add(new Deadline("submit assignment", "30/01/2026 1800"));
        storage.saveToFile(tasks);

        Storage loader = new Storage(temp.getPath());
        TaskList loaded = new TaskList();
        loader.loadStoredTasks(loaded);

        assertEquals(2, loaded.size());
        assertEquals("[T][ ] return book", loaded.get(1).toString());
        assertEquals("[D][ ] submit assignment (by: Jan 30 2026, 18:00)", loaded.get(2).toString());
    }

    /**
     * Tests that the storage correctly loads tasks from a file.
     * Writes fake task data to a temporary file in the storage format, then verifies
     * that loading the file correctly parses and reconstructs the tasks.
     *
     * @throws IOException if there are file I/O errors during the test.
     */
    @Test
    public void loadStoredTasks_readsCorrectly() throws IOException {
        File temp = new File("data/testLoad.txt");

        // write fake saved data
        temp.getParentFile().mkdirs();
        java.nio.file.Files.write(temp.toPath(), (
                        "T | 0 | walk dog\n"
                        + "E | 1 | party | 12/02/2026 1700-12/02/2026 1800\n").getBytes()
        );

        Storage storage = new Storage(temp.getPath());
        TaskList list = new TaskList();
        storage.loadStoredTasks(list);

        assertEquals(2, list.size());
        assertEquals("[T][ ] walk dog", list.get(1).toString());
        assertEquals("[E][X] party (from: Feb 12 2026, 17:00 to: Feb 12 2026, 18:00)", list.get(2).toString());
    }
}
