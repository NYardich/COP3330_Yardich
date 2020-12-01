import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class TaskItemTest {
    /*       Constructor           */
    // If date doesn't exist
    @Test
    public void constructorFailsWithInvalidYYMMDD() {
        assertThrows(DateTimeException.class, () -> {
            TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2000-20-20"));
        });
    }

    // If date isn't a date
    @Test
    public void constructorFailsWithInvalidDateFormat() {
        assertThrows(DateTimeException.class, () -> {
            TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("bagel"));
        });
    }

    // If date is in the past
    @Test
    public void constructorFailsWithInvalidDueDate() {
        assertThrows(NamingException.class, () -> {
            TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2020-11-10"));
        });
    }

    @Test
    public void constructorFailsWithInvalidTitle() {
        assertThrows(NamingException.class, () -> {
            TaskItem test = new TaskItem(" ", "Test", LocalDate.parse("2021-01-01"));
        });
    }

    @Test
    public void constructorSucceedsWithValidDueDate() throws NamingException {
        assertDoesNotThrow(() -> new TaskItem("Test", "test", LocalDate.parse("2021-01-01")));
        TaskItem test = new TaskItem("Test", "test", LocalDate.parse("2021-01-01"));
        assertEquals(test.toString(), "[2021-01-01] Test: test");
    }
    @Test
    public void constructorSucceedsWithValidTitle() throws NamingException {
        assertDoesNotThrow(() -> new TaskItem("Test", "test", LocalDate.parse("2021-01-01")));
        TaskItem test = new TaskItem("Test", "test", LocalDate.parse("2021-01-01"));
        assertEquals(test.toString(), "[2021-01-01] Test: test");
    }

    /*          Editing            */
    @Test
    public void editingDescriptionSucceedsWithExpectedValue() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertDoesNotThrow(() -> test.setDescription("West"));
        assertEquals(test.toString(), "[2021-01-01] Test: West");
    }

    // If the date isn't a date
    @Test
    public void editingDueDateFailsWithInvalidDateFormat() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(DateTimeException.class, () -> {
            test.setDueDate(LocalDate.parse("bagel"));
        });
    }

    // If the date doesn't exist
    @Test
    public void editingDueDateFailsWithInvalidYYMMDD() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(DateTimeParseException.class, () -> {
            test.setDueDate(LocalDate.parse("2020-12-40"));
        });
    }

    // If the date is in the past
    @Test
    public void editingDueDateFailsWithInvalidDate() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(NamingException.class, () -> {
            test.setDueDate(LocalDate.parse("2020-09-09"));
        });
    }

    @Test
    public void editingDueDateSucceedsWithValidDate() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertDoesNotThrow(() -> test.setDueDate(LocalDate.parse("2021-02-14")));
        assertEquals(test.getDueDate().toString(),"2021-02-14");
    }

    @Test
    public void editingTitleFailsWithEmptyString() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(NamingException.class, () -> {
            test.setTitle("");
        });
    }

    @Test
    public void editingTitleFailsWithEBlankString() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertThrows(NamingException.class, () -> {
            test.setTitle(" ");
        });
    }

    @Test
    public void editingTitleSucceedsWithExpectedValue() throws NamingException {
        TaskItem test = new TaskItem("Test", "Test", LocalDate.parse("2021-01-01"));
        assertDoesNotThrow(() -> test.setTitle("Hello"));
        assertEquals(test.getTitle(),"Hello");
    }
}