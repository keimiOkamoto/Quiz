package models;

import com.google.inject.Singleton;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

@Singleton
public class ScoreKeeperImpl implements ScoreKeeper {
    private Map<Integer, List<Remote>> scoreBoardMap = new HashMap<>();

    protected ScoreKeeperImpl() throws RemoteException {
    }

    @Override
    public void addHighScore(Quiz quiz, Player player) {
        if (!highScoreContains(quiz) || scoreIsHighest(quiz)) {
            setLeader(quiz, player);
        }
    }

    @Override
    public boolean highScoreContains(Quiz quiz) {
        boolean result = false;
        try {
            result = scoreBoardMap.containsKey(quiz.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getHighScore(Quiz quiz) {
        List<Remote> list = null;
        int score = 0;
        try {
            list = scoreBoardMap.get(quiz.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            score = ((Quiz) list.get(0)).getScore();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return score;
    }

    @Override
    public Player getLeader(int quizId) {
        List<Remote> list = scoreBoardMap.get(quizId);
        return (Player)list.get(1);
    }

    @Override
    public void setLeader(Quiz quiz, Player player) {
        List<Remote> list = Arrays.asList(quiz, player);
        try {
            scoreBoardMap.put(quiz.getId(),list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        boolean result = false;
        if (highScoreContains(quiz)) {
            List<Remote> list = null;
            try {
                list = scoreBoardMap.get(quiz.getId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            int currentHighest = 0;
            try {
                currentHighest = ((Quiz) list.get(0)).getScore();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                if (quiz.getScore() >= currentHighest) {
                    result = true;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
