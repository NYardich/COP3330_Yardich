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
    public void addingTaskItemsIncreasesSize() {
        TaskList test = new TaskList();
        int oldSize = test.size();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int newSize = test.size();
        assert(newSize > oldSize);
    }
    @Test
    public void completingTaskItemChangesStatus() {
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
    public void editingTaskItemChangesValues() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "test", "tesT", LocalDate.parse("2020-12-24"));
        assertEquals(test.get(0).toString(), "[2020-12-24] test: tesT");
    }
    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "test", "tesTing", LocalDate.parse("2020-12-20"));
        assertEquals(test.get(0).toString(), "[2020-12-20] test: tesTing");
    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "Test", "test", LocalDate.parse("2020-12-20"));
        assertEquals(test.get(0).toString(), "[2020-12-20] Test: test");
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "test", "tesTing", LocalDate.parse("2020-12-20"));
        });
    }
    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        test.editItem(0, "Testing", "test", LocalDate.parse("2020-12-12"));
        assertEquals(test.get(0).toString(), "[2020-12-12] Testing: test");
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).toString(), "[2020-12-12] Test: test");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.editItem(1, "Testing", "test", LocalDate.parse("2020-12-12"));
        });
    }
    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getDescription();
        });
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).getDescription(), "test");
    }
    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getDueDate();
        });
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertEquals(test.get(0).getDueDate(), LocalDate.parse("2020-12-12"));
    }
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.get(1).getTitle();
        });
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
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
    public void removingTaskItemsDecreasesSize() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        int oldSize = test.size();
        test.deleteItem(0);
        int newSize = test.size();
        assert(oldSize > newSize);
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            test.deleteItem(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded() throws FileNotFoundException {
        TaskList current = new TaskList();
        current.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        TaskList ret = new TaskList();

        // Writing to file
        try(Formatter output = new Formatter("test.txt")) {
            for(int i = 0; i < current.size(); i++) {
                output.format("%s;%s;%s;%s;", current.get(i).getTitle(), current.get(i).getDescription(), current.get(i).getDueDate(), current.get(i).isCompleted());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or created");
        } catch (Exception e) {
            System.out.println("Unknown error in File I/O");
        }

        // Reading from file
        try {
            Scanner sc = new Scanner(new File("test.txt"));
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String title = sc.next();
                String description = sc.next();
                LocalDate dueDate = LocalDate.parse(sc.next());
                boolean completed = Boolean.valueOf(sc.next());
                ret.addItem(new TaskItem(title, description, dueDate, completed));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        assertEquals(current.toString(), ret.toString());
    }
    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.get(0).setCompleted(true);
        assertEquals(true, test.get(0).isCompleted());
        test.get(0).setCompleted(false);
        assertEquals(false, test.get(0).isCompleted());
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList test = new TaskList();
        test.addItem(new TaskItem("Test", "test", LocalDate.parse("2020-12-12")));
        test.get(0).setCompleted(true);
        assertEquals(true, test.get(0).isCompleted());
        assertThrows(IndexOutOfBoundsException.class, () -> {
                test.get(1).setCompleted(false);
        });
    }
}