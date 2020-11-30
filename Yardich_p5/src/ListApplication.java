public abstract class ListApplication<E, T> {

    public abstract void mainMenu();
    protected abstract boolean mainMenuDecision(int response);
    protected abstract void listOperationMenu(E current);
    protected abstract boolean listOperationMenuDecision(int response, E current);
    protected abstract T addPrompt();
    protected abstract void removePrompt(E current);
    protected abstract void editPrompt(E current);
    protected abstract void writeToFile(E current);

    // All readFile variants should throw an exception, since they can all fail
    protected abstract E readFile() throws Exception;

}
