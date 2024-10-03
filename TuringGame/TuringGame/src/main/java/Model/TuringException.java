package Model;

/**
 * The TuringException class is a custom exception that extends RuntimeException.
 * It is used to indicate specific conditions that occur during the execution of the program
 * and are specific to the context of the application.
 */
public class TuringException extends RuntimeException {

    /**
     * Constructor for TuringException.
     * This constructor creates a new exception with a specified message.
     *
     * @param message The detailed message explaining the reason for the exception.
     *                This message can later be retrieved by the Throwable.getMessage() method.
     */
    public TuringException(String message) {
        super(message); // Calls the constructor of RuntimeException with an error message
    }
}