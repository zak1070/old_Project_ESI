package Command;



public interface Command {
    /**
     * Executes the specified command
     */
    void execute();


    /**
     * undo the command
     */
    void unExecute();
}
