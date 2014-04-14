package views;

import java.util.Scanner;

/**
 * In charge of closing quiz and all the logic that
 * goes with it.
 */
public interface QuizCloser {

    /**
     * Closers a quiz with an ID.
     *
     * @param message A message of the current state of the event.
     * @param scanner A scanner object.
     * @return Returns a message for the next scenario.
     */
    String closeQuizWithId(String message, Scanner scanner);
}
