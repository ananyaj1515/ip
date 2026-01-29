package duke;

import duke.task.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

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

    @Test
    public void loadStoredTasks_readsCorrectly() throws IOException {
        File temp = new File("data/testLoad.txt");

        // write fake saved data
        temp.getParentFile().mkdirs();
        java.nio.file.Files.write(temp.toPath(),
                ("T | 0 | walk dog\n" +
                        "E | 1 | party | 12/02/2026 1700-12/02/2026 1800\n").getBytes());

        Storage storage = new Storage(temp.getPath());
        TaskList list = new TaskList();
        storage.loadStoredTasks(list);

        assertEquals(2, list.size());
        assertEquals("[T][ ] walk dog", list.get(1).toString());
        assertEquals("[E][X] party (from: Feb 12 2026, 17:00 to: Feb 12 2026, 18:00)", list.get(2).toString());
    }
}
