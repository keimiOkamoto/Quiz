package utils;


import controllers.ClosedQuizContainer;
import models.Quiz;

import java.util.TreeMap;

/**
 * Disk writer is a class that is responsible for the data
 * to be written to disk. Reads the date from disk.
 */
public interface DiskWriter {
    /**
     * Writes data to disk. Data is serialized here.
     *
     * @param closedQuizContainer a QuizContainer
     * @param treeMap the
     */
    void writeToDisk(ClosedQuizContainer closedQuizContainer, TreeMap<Integer, Quiz> treeMap);

    /**
     * Checks of the data exists
     *
     * @return true if data exists.
     */
    boolean checkIfDataExists();

    /**
     * Reads data from disk
     */
    void readDisk();

    /**
     * Checks of the data exists for uniqueNumberGenerator.
     *
     * @return true if i does.
     */
    boolean checkIfIdDataExists();

    /**
     * Writes the data to disk.
     *
     * @param id the number generated.
     */
    void writeIdToDisk(Integer id);

    /**
     * Reads the disk for the previously
     * saved file.
     */
    void readDiskForIdFile();

    /**
     * Gets the closed quiz container.
     *
     * @return A closed quiz container.
     */
    ClosedQuizContainer getClosedQuizContainer();

    /**
     * Gets a tree map of previously saved quizzes.
     *
     * @return Tree map of previously saved quizzes.
     */
    TreeMap<Integer,Quiz> getQuizTreeMap();

    /**
     * Gets a UniqueNumberGenerator.
     *
     * @return An integer of where it was last saved.
     */
    Integer getUniqueNumber();

}
