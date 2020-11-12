import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        assertThrows(DateTimeException.class, () -> {
            TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2000-20-20"));
        });
    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(InputMismatchException.class, () -> {
            TaskItem test = new TaskItem("", "Test", LocalDate.parse("2021-01-01"));
        });
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() throws Exception {
        TaskItem test = new TaskItem("Test", "test", LocalDate.parse("2021-01-01"));
        assertEquals(test.toString(), "[2021-01-01] Test: test");
    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle() throws Exception {
        TaskItem test = new TaskItem("Test", "test", LocalDate.parse("2021-01-01"));
        assertEquals(test.toString(), "[2021-01-01] Test: test");
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() throws Exception {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(DateTimeException.class, () -> {
            test.setDueDate(LocalDate.parse("2000-20-20"));
        });
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() throws Exception {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        test.setDueDate(LocalDate.parse("2021-02-14"));
        assertEquals(test.getDueDate().toString(),"2021-02-14");
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() throws Exception {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(InputMismatchException.class, () -> {
            test.setTitle("");
        });
    }
    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() throws Exception {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        test.setTitle("Hello");
        assertEquals(test.getTitle(),"Hello");
    }
}