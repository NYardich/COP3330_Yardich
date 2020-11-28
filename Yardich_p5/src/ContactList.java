import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContactList implements ItemList<ContactItem>{
    private ArrayList<ContactItem> items;

    ContactList() { this.items = new ArrayList<>(); }

    @Override
    public void addItem(ContactItem item) {
        items.add(item);
    }

    @Override
    public void deleteItem(int index) {
        items.remove(index);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void write(String filename) {

    }

    @Override
    public void read(String filename) throws FileNotFoundException {

    }
}
