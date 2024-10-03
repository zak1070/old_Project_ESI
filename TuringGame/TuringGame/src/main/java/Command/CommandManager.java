package Command;

import Model.TuringException;

import java.util.Stack;

/**
 * The CommandManager class manages the execution, undo, and redo of commands.
 * It utilizes stacks to keep track of the command history.
 */
public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Executes a given command and manages its state for undo/redo functionality.
     * The command is added to the undo stack, and the redo stack is cleared.
     *
     * @param command The command to be executed.
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clears the redo history when a new command is executed
    }

    /**
     * Undoes the most recently executed command, if available.
     * The command is moved from the undo stack to the redo stack.
     *
     * @throws TuringException If there are no commands to undo.
     */
    public void undo() {
        if (undoStack.isEmpty()) {
            throw new TuringException("Undo failed");
        }
        Command command = undoStack.pop();
        command.unExecute();
        redoStack.push(command); // Add the command to the redo stack
    }

    /**
     * Redoes the most recently undone command, if available.
     * The command is moved from the redo stack back to the undo stack.
     *
     * @throws TuringException If there are no commands to redo.
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            throw new TuringException("Redo failed");
        }
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command); // Put the command back on the undo stack
    }

    public int getRedoStackSize() {
        return this.redoStack.size();
    }
}