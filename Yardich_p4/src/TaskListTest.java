import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() throws Exception {
        TaskList test = new TaskList();
        int oldSize = test.size();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int newSize = test.size();
        assert(newSize > oldSize);
    }
    @Test
    public void completingTaskItemChangesStatus() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.get(0).setCompleted(true);
        assertEquals(true, test.get(0).isCompleted());
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(0).setCompleted(true);
        });
    }
    @Test
    public void editingTaskItemChangesValues() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "test", "tesT", LocalDate.parse("2020-12-24"));
        assertEquals(test.get(0).toString(), "[2020-12-24] test: tesT");
    }
    @Test
    public void editingTaskItemDescriptionChangesValue() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "test", "tesTing", LocalDate.parse("2020-12-20"));
        assertEquals(test.get(0).toString(), "[2020-12-20] test: tesTing");
    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemDueDateChangesValue() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "Test", "test", LocalDate.parse("2020-12-20"));
        assertEquals(test.toString(), "0) [2020-12-20] Test: test");
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemTitleChangesValue() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "Testing", "test", LocalDate.parse("2020-12-12"));
        assertEquals(test.toString(), "0) [2020-12-12] Testing: test");
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "Testing", "test", LocalDate.parse("2020-12-12"));
        });
    }
    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getDescription();
        });
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).getDescription(), "test");
    }
    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getDueDate();
        });
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).getDueDate(), LocalDate.parse("2020-12-12"));
    }
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getTitle();
        });
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).getTitle(), "Test");
    }
    @Test
    public void newTaskListIsEmpty() {
        TaskList test = new TaskList();
        assertEquals(test.size(), 0);
    }
    @Test
    public void removingTaskItemsDecreasesSize() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int oldSize = test.size();
        test.deleteItem(0);
        int newSize = test.size();
        assert(oldSize > newSize);
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.deleteItem(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded() throws Exception{
        TaskList current = new TaskList();
        current.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        TaskList ret = new TaskList();

        current.write("test");
        ret.read("test");

        assertEquals(current.toString(), ret.toString());
    }

    // This test depends on there not being a file named !!//\\.txt in the home directory
    @Test
    public void ReadingTaskListFailsWithNonexistentFile() throws Exception {
        TaskList current = new TaskList();

        assertThrows(FileNotFoundException.class, () -> {
            current.read("!!//\\");
        });
    }

    @Test
    public void uncompletingTaskItemChangesStatus() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.get(0).setCompleted(true);
        assertEquals(true, test.get(0).isCompleted());
        test.get(0).setCompleted(false);
        assertEquals(false, test.get(0).isCompleted());
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() throws Exception {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.get(0).setCompleted(true);
        assertEquals(true, test.get(0).isCompleted());
        assertThrows(IndexOutOfBoundsException.class, () -> {
                test.get(1).setCompleted(false);
        });
    }
}