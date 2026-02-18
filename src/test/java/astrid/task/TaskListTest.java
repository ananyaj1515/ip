package astrid.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import astrid.TaskList;

public class TaskListTest {

    @Test
    public void addAndGetAndRemove_tasks_workCorrectly() {
        TaskList list = new TaskList();

        Task t1 = new ToDo("read book");
        Task t2 = new ToDo("write essay");
        list.add(t1);
        list.add(t2);

        assertEquals(2, list.size());

        assertEquals(t1, list.get(1));
        assertEquals(t2, list.get(2));

        Task removed = list.remove(1);
        assertEquals(t1, removed);
        assertEquals(1, list.size());
        assertEquals(t2, list.get(1));
    }

    @Test
    public void isEmptyAndSize_checks_workCorrectly() {
        TaskList list = new TaskList();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        list.add(new ToDo("exercise"));
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        list.remove(1);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}
