import java.util.Scanner;

// abstract class for applications based around ItemLists; used by ContactApp and TaskApp
public abstract class ListApplication<E, T> {
    static Scanner input = new Scanner(System.in);

    public abstract void mainMenu();
    protected abstract boolean mainMenuDecision(int response);
    protected abstract void listOperationMenu(E current);
    protected abstract boolean listOperationMenuDecision(int response, E current);
    protected abstract T addPrompt();
    protected abstract void removePrompt(E current);
    protected abstract void editPrompt(E current);
    protected abstract void writeToFile(E current);

    // All readFile variants should throw an exception, to signal to callers if File I/O fails
    protected abstract E readFile() throws Exception;

}
