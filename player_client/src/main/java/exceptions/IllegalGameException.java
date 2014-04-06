package exceptions;

/**
 * Exception for an illegal game.
 * i.e If there are no games available.
 *
 */
public class IllegalGameException extends Exception {

    public IllegalGameException(String message) {
        super(message);
    }
}
