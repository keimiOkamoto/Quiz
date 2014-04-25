package utils;

import com.google.inject.Singleton;
import controllers.ClosedQuizContainer;
import models.HighScore;
import models.Quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

@Singleton
public class DiskWriterImpl implements DiskWriter {

    private static final String QUIZ_TXT = "quiz.txt";
    private static final String ID_TXT = "id.txt";
    private static final String HIGH_SCORE_TXT = "high_score.txt";

    private ClosedQuizContainer closedQuizContainer;
    private TreeMap<Integer, Quiz> treeMap;
    private Map<Integer, HighScore> scoreBoardMap;
    private Integer id;

    public DiskWriterImpl() {
        addShutdownHook();
    }

    @Override
    public void writeToDisk() {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(DiskWriterImpl.QUIZ_TXT);
            out = new ObjectOutputStream(fos);
            out.writeObject(closedQuizContainer);
            out.writeObject(treeMap);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkIfDataExists() {
        File file = new File(DiskWriterImpl.QUIZ_TXT);
        return file.exists();
    }

    @Override
    public void readDisk() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.QUIZ_TXT);
            in = new ObjectInputStream(fis);
            closedQuizContainer = (ClosedQuizContainer) in.readObject();
            treeMap = (TreeMap<Integer, Quiz>) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClosedQuizContainer getClosedQuizContainer() {
        return closedQuizContainer;
    }

    @Override
    public TreeMap<Integer, Quiz> getQuizTreeMap() {
        return treeMap;
    }

    /*
    HighScore object
     */
    @Override
    public void writeHighScoreBoardToDisk() {
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = new FileOutputStream(DiskWriterImpl.HIGH_SCORE_TXT);
            out = new ObjectOutputStream(fos);
            out.writeObject(scoreBoardMap);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean checkIfHighScoreDataExists() {
        return new File(DiskWriterImpl.HIGH_SCORE_TXT).exists();
    }

    @Override
    public void readDiskForHighScore() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.HIGH_SCORE_TXT);
            in = new ObjectInputStream(fis);
            scoreBoardMap = (Map<Integer, HighScore>) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, HighScore> getHighScoreBoardMap() {
        readDiskForHighScore();
        return scoreBoardMap;
    }

    /*
    Id object
     */
    @Override
    public void writeIdToDisk(Integer id) {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(DiskWriterImpl.ID_TXT);
            out = new ObjectOutputStream(fos);
            out.writeObject(id);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getUniqueNumber() {
        return id;
    }

    @Override
    public boolean checkIfIdDataExists() {
        File file = new File(DiskWriterImpl.ID_TXT);
        return file.exists();
    }

    @Override
    public void readDiskForIdFile() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.ID_TXT);
            in = new ObjectInputStream(fis);
            id = (Integer) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        writeToDisk();
        writeHighScoreBoardToDisk();
    }

    @Override
    public void persist(ClosedQuizContainer closedQuizContainer, TreeMap<Integer, Quiz> treeMap) {
        this.closedQuizContainer = closedQuizContainer;
        this.treeMap = treeMap;
    }

    @Override
    public void persist(Map<Integer, HighScore> scoreBoardMap) {
        this.scoreBoardMap = scoreBoardMap;
    }

    /**
     * This method adds a shutdown hook
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                flush();
            }
        });
    }
}
