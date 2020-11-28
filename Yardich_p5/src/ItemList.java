import java.io.FileNotFoundException;

// Generic interface for lists composed of items E
public interface ItemList<E> {

    void addItem(E item);
    void deleteItem(int index);
    int size();
    void write(String filename);
    // All implementations of a file read method involve throwing a FileNotFoundException because of the nature of File I/O
    void read(String filename) throws FileNotFoundException;
    E get(int index);
}
