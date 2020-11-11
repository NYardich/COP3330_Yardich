import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        try {
            //TaskItem temp = new TaskItem("Hello", "2018-13-20");
            fail(); // exception wasn't thrown
        }
        catch (Exception e) {
            final String expected = "Invalid Due Date";
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        try {
            //TaskItem temp = new TaskItem("", "2020-12-20");
            fail(); // exception wasn't thrown
        }
        catch (Exception e) {
            final String expected = "Invalid Title";
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        try {
            //TaskItem temp = new TaskItem("Hello", "2020-12-20");
            //assertEquals(temp.getDueDate(), LocalDate.parse("2020-12-20"));
        }
        catch (Exception e) {
            fail();
        }
    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        try {
            //TaskItem temp = new TaskItem("Hello", "2020-12-20");
            //assertEquals(temp.getTitle(), "Hello");
        }
        catch (Exception e) {
            fail();
        }
    }
    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        try {
            //TaskItem temp = new TaskItem("Hello", "2020-12-20");
            //temp.setDueDate("2020-13-20");
            fail();
        }
        catch (Exception e) {
            final String expected = "Invalid Date";
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {

    }
    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {

    }
    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {

    }
}