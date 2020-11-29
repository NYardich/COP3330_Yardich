import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class ContactListTest {
    @Test
    public void addingItemsIncreasesSize() throws Exception {
        ContactList test = new ContactList();
        int oldSize = test.size();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        int newSize = test.size();
        assert (newSize > oldSize);
    }
    @Test
    public void editingItemsFailsWithAllBlankValues() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertThrows(Exception.class, () -> {
            test.editItem(0, "", "", "", "");
        });
    }
    @Test
    public void editingItemsFailsWithInvalidIndex() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertThrows(Exception.class, () -> {
            test.editItem(1, "Test", "test", "111-111-1111", "x@y.z");
        });
    }
    @Test
    public void editingSucceedsWithBlankFirstName() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertDoesNotThrow(() -> test.editItem(0, "", "test", "111-111-1111", "x@y.z"));
        assertEquals(test.toString(), "0)%nName:  test%nPhone: 111-111-1111%nEmail: x@y.z%n");

    }
    @Test
    public void editingSucceedsWithBlankLastName() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertDoesNotThrow(() -> test.editItem(0, "Test", "", "111-111-1111", "x@y.z"));
        assertEquals(test.toString(), "0)%nName: Test %nPhone: 111-111-1111%nEmail: x@y.z%n");
    }
    @Test
    public void editingSucceedsWithBlankPhone() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertDoesNotThrow(() -> test.editItem(0, "Test", "test", "", "x@y.z"));
        assertEquals(test.toString(), "0)%nName: Test test%nPhone: %nEmail: x@y.z%n");
    }
    @Test
    public void editingSucceedsWithNonBlankValues() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertDoesNotThrow(() -> test.editItem(0, "test", "Test", "222-222-2222", "a@b.c"));
        assertEquals(test.toString(), "0)%nName: test Test%nPhone: 222-222-2222%nEmail: a@b.c%n");
    }
    @Test
    public void newListIsEmpty() {
        ContactList test = new ContactList();
        assertEquals(test.size(), 0);
    }
    @Test
    public void removingItemsDecreasesSize() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        int oldSize = test.size();
        test.deleteItem(0);
        int newSize = test.size();
        assert (newSize < oldSize);
    }
    @Test
    public void removingItemsFailsWithInvalidIndex() throws Exception {
        ContactList test = new ContactList();
        test.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        assertThrows(Exception.class, () -> {
            test.deleteItem(1);
        });
    }
    @Test
    public void savedContactListCanBeLoaded() throws Exception {
        ContactList current = new ContactList();
        current.addItem(new ContactItem("Test", "test", "111-111-1111", "x@y.z"));
        ContactList ret = new ContactList();

        current.write("contactTest");
        ret.read("contactTest");

        assertEquals(current.toString(), ret.toString());
    }
}
