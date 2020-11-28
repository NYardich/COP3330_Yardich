import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() throws NamingException {
        TaskList test = new TaskList();
        int oldSize = test.size();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int newSize = test.size();
        assert (newSize > oldSize);
    }

    @Test
    public void completingTaskItemChangesStatus() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.makeComplete(0);
        assertEquals(true, test.isComplete(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.makeComplete(0);
        });
    }

    @Test
    public void editingTaskItemChangesValues() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "test", "tesT", LocalDate.parse("2020-12-24"));
        assertEquals(test.toString(), "0) [2020-12-24] test: tesT");
    }
    @Test
    public void editingTaskItemDescriptionChangesValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "test", "tesTing", LocalDate.parse("2020-12-20"));
        assertEquals(test.toString(), "0) [2020-12-20] test: tesTing");
    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemDueDateChangesValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "Test", "test", LocalDate.parse("2020-12-20"));
        assertEquals(test.toString(), "0) [2020-12-20] Test: test");
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemTitleChangesValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        test.editItem(0, "Testing", "test", LocalDate.parse("2020-12-12"));
        assertEquals(test.toString(), "0) [2020-12-12] Testing: test");
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "Testing", "test", LocalDate.parse("2020-12-12"));
        });
    }
    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getDescription(1);
        });
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getDescription(0), "test");
    }
    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getDueDate(1);
        });
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getDueDate(0), LocalDate.parse("2020-12-12"));
    }
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getTitle(1);
        });
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getTitle(0), "Test");
    }
    @Test
    public void newTaskListIsEmpty() {
        TaskList test = new TaskList();
        assertEquals(test.size(), 0);
    }
    @Test
    public void removingTaskItemsDecreasesSize() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int oldSize = test.size();
        test.deleteItem(0);
        int newSize = test.size();
        assert(oldSize > newSize);
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.deleteItem(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded() throws NamingException, FileNotFoundException {
        TaskList current = new TaskList();
        current.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        TaskList ret = new TaskList();

        current.write("test");
        ret.read("test");

        assertEquals(current.toString(), ret.toString());
    }

    // This test depends on there not being a file named !!//\\.txt in the home directory
    @Test
    public void ReadingTaskListFailsWithNonexistentFile() throws NamingException {
        TaskList current = new TaskList();

        assertEquals(false, current.read("!!//\\"));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.makeComplete(0);
        assertEquals(true, test.isComplete(0));
        test.makeIncomplete(0);
        assertEquals(false, test.isComplete(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.makeComplete(0);
        assertEquals(true, test.isComplete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
                test.makeIncomplete(1);
        });
    }
}