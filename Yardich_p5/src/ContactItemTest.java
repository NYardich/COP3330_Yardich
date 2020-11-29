import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactItemTest {

    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(Exception.class, () -> {
            ContactItem test = new ContactItem("", "", "", "");
        });
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("a", "b", "111-111-1111", ""));
    }

    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("", "b", "111-111-1111", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("a", "", "111-111-1111", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("a", "b", "", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("a", "b", "111-111-1111", "x@y.z"));
    }

    @Test
    public void editingFailsWithAllBlankValues() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertThrows(Exception.class, () -> {
            test.editItem("","","","");
        });
    }

    @Test
    public void editingSucceedsWithBlankEmail() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertDoesNotThrow(() -> test.editItem("b","c","222-222-2222",""));
        assertEquals(test.toString(), "Name: b c%nPhone: 222-222-2222%nEmail: ");
    }

    @Test
    public void editingSucceedsWithBlankFirstName() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertDoesNotThrow(() -> test.editItem("","c","222-222-2222","a@b.c"));
        assertEquals(test.toString(), "Name:  c%nPhone: 222-222-2222%nEmail: a@b.c");
    }

    @Test
    public void editingSucceedsWithBlankLastName() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertDoesNotThrow(() -> test.editItem("b","","222-222-2222","a@b.c"));
        assertEquals(test.toString(), "Name: b %nPhone: 222-222-2222%nEmail: a@b.c");
    }

    @Test
    public void editingSucceedsWithBlankPhone() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertDoesNotThrow(() -> test.editItem("b","c","","a@b.c"));
        assertEquals(test.toString(), "Name: b c%nPhone: %nEmail: a@b.c");
    }

    @Test
    public void editingSucceedsWithNonBlankValues() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertDoesNotThrow(() -> test.editItem("b","c","222-222-2222","a@b.c"));
        assertEquals(test.toString(), "Name: b c%nPhone: 222-222-2222%nEmail: a@b.c");
    }

    @Test
    public void testToString() throws Exception {
        ContactItem test = new ContactItem("a", "b", "111-111-1111", "x@y.z");
        assertEquals(test.toString(), "Name: a b%nPhone: 111-111-1111%nEmail: x@y.z");
    }
}
