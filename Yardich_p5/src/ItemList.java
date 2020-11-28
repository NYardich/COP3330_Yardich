import java.io.FileNotFoundException;

// Generic interface for lists composed of items E
public interface ItemList<E> {

    void addItem(E item);
    void deleteItem(int index);
    int size();
    void write(String filename);
    boolean read(String filename);
}
