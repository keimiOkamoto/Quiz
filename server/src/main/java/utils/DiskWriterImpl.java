package utils;

import controllers.ClosedQuizContainer;
import models.Quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;


public class DiskWriterImpl implements DiskWriter {
    private static final String filename = "quiz.txt";
    private static final String idFile = "id.txt";

    private ClosedQuizContainer closedQuizContainer;
    private TreeMap<Integer, Quiz> treeMap;
    private Integer id;

    @Override
    public void writeToDisk(ClosedQuizContainer closedQuizContainer, TreeMap<Integer, Quiz> treeMap) {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(DiskWriterImpl.filename);
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
        File file = new File(DiskWriterImpl.filename);
        return file.exists();
    }

    @Override
    public void readDisk() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.filename);
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

    @Override
    public Integer getUniqueNumber() {
        return id;
    }


    @Override
    public boolean checkIfIdDataExists() {
        File file = new File(DiskWriterImpl.idFile);
        return file.exists();
    }

    @Override
    public void writeIdToDisk(Integer id) {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(DiskWriterImpl.idFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(id);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDiskForIdFile() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.idFile);
            in = new ObjectInputStream(fis);
            id = (Integer) in.readObject();
            System.out.println(id);
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}
