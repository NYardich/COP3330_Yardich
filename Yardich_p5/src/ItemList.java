// Generic interface for lists composed of items E; utilized by TaskList and ContactList
public interface ItemList<E> {

    void addItem(E item);
    void deleteItem(int index);
    int size();
    void write(String filename);
    boolean read(String filename);

}
