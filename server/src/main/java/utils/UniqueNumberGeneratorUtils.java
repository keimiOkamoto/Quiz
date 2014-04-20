package utils;

/**
 * A unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
public interface UniqueNumberGeneratorUtils {
    /**
     * Method that gets a unique number;
     *
     * @return a unique number
     */
    int getUniqueNumber();

    /**
     * Serializes the current state of the
     * uniqueNumberGenerator when the server
     * shutsdown.
     */
    void flush();
}
