import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;

class TaskListTest {

    /*          Basics            */
    @Test
    public void addingItemsIncreasesSize() throws NamingException {
        TaskList test = new TaskList();
        int oldSize = test.size();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int newSize = test.size();
        assert (newSize > oldSize);
    }

    @Test
    public void newListIsEmpty() {
        TaskList test = new TaskList();
        assertEquals(test.size(), 0);
    }

    /*          Completing      */
    @Test
    public void completingTaskItemChangesStatus() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.setComplete(0, true);
        assertEquals(true, test.isComplete(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.setComplete(0, true);
        });
    }

    /*    Editing      */
    @Test
    public void editingTaskItemChangesValues() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        test.editItem(0, "test", "tesT", LocalDate.parse("2020-12-24"));
        assertEquals(test.toString(), "0) [2020-12-24] test: tesT%n");
    }
                // Description
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertDoesNotThrow(() -> test.editItem(0, "test", "tesTing", LocalDate.parse("2020-12-20")));
        assertEquals(test.toString(), "0) [2020-12-20] test: tesTing%n");
    }
                // Due Date
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertDoesNotThrow(() -> test.editItem(0, "Test", "test", LocalDate.parse("2020-12-20")));
        assertEquals(test.toString(), "0) [2020-12-20] Test: test%n");
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(DateTimeException.class, () -> {
            test.editItem(0, "test", "tesTing", LocalDate.parse("Bread"));
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYYMMDD() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(DateTimeException.class, () -> {
            test.editItem(0, "test", "tesTing", LocalDate.parse("2020-12-40"));
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDueDate() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(NamingException.class, () -> {
            test.editItem(0, "test", "tesTing", LocalDate.parse("2020-09-09"));
        });
    }
                // Title
    @Test
    public void editingItemTitleSucceedsWithExpectedValue() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertDoesNotThrow(() -> test.editItem(0, "Testing", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Testing: test%n");
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "Testing", "test", LocalDate.parse("2020-12-12"));
        });
    }
    @Test
    public void editingTaskItemTitleFailsWithEmptyString() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(NamingException.class, () -> {
            test.editItem(0, "", "test", LocalDate.parse("2020-12-12"));
        });
    }
    @Test
    public void editingTaskItemTitleFailsWithBlankString() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.toString(), "0) [2020-12-12] Test: test%n");
        assertThrows(NamingException.class, () -> {
            test.editItem(0, " ", "test", LocalDate.parse("2020-12-12"));
        });
    }

    /*      Getters        */
                // Description
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getDescription(1);
        });
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getDescription(0), "test");
    }
                // Due Date
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getDueDate(1);
        });
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getDueDate(0), LocalDate.parse("2020-12-12"));
    }
                // Title
    @Test
    public void gettingItemTitleFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.getTitle(1);
        });
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.getTitle(0), "Test");
    }

    /*      Removing        */
    @Test
    public void removingItemsDecreasesSize() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int oldSize = test.size();
        test.deleteItem(0);
        int newSize = test.size();
        assert(oldSize > newSize);
    }
    @Test
    public void removingItemsFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.deleteItem(1);
        });
    }

    /*       Saving/Loading           */
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


    /*              Uncompleting            */
    @Test
    public void uncompletingTaskItemChangesStatus() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.setComplete(0, true);
        assertEquals(true, test.isComplete(0));
        test.setComplete(0, false);
        assertEquals(false, test.isComplete(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() throws NamingException {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.setComplete(0, true);
        assertEquals(true, test.isComplete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
                test.setComplete(1,false);
        });
    }
}