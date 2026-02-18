package astrid.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class ToDoTest {

    @Test
    public void toString_normal_success() {
        Task todo = new ToDo("find a job");
        assertEquals("[T][ ] find a job", todo.toString());
    }
}
