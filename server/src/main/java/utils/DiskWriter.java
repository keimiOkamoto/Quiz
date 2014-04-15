package utils;


import controllers.ClosedQuizContainer;
import controllers.QuizContainer;
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



    ClosedQuizContainer getClosedQuizContainer();

    TreeMap<Integer,Quiz> getQuizTreeMap();

    Integer getUniqueNumber();

    void writeIdToDisk(Integer id);

    boolean checkIfIdDataExists();

    void readDiskForIdFile();


    /**
     * Returns a MeetingContainer
     *
     * @return a MeetingContainer
     */
   // MeetingContainer getMeetingContainer();
}
