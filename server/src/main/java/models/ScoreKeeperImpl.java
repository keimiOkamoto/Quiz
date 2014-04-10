package models;

import com.google.inject.Singleton;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

@Singleton
public class ScoreKeeperImpl extends UnicastRemoteObject implements ScoreKeeper {
    private Map<Integer, List<Remote>> scoreBoardMap = new HashMap<>();

    protected ScoreKeeperImpl() throws RemoteException {
    }

    @Override
    public void addHighScore(Quiz quiz, Player player) throws RemoteException {
        if (!highScoreContains(quiz) || scoreIsHighest(quiz)) {
            setLeader(quiz, player);
        }
    }

    @Override
    public boolean highScoreContains(Quiz quiz) throws RemoteException {
        return scoreBoardMap.containsKey(quiz.getId());
    }

    @Override
    public int getHighScore(Quiz quiz) throws RemoteException {
        List<Remote> list = scoreBoardMap.get(quiz.getId());
        return ((Quiz) list.get(0)).getScore();
    }

    @Override
    public Player getLeader(int quizId) {
        List<Remote> list = scoreBoardMap.get(quizId);
        return (Player)list.get(1);
    }

    @Override
    public void setLeader(Quiz quiz, Player player) throws RemoteException {
        List<Remote> list = Arrays.asList(quiz, player);
        scoreBoardMap.put(quiz.getId(),list);
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) throws RemoteException {
        boolean result = false;
        if (highScoreContains(quiz)) {
            List<Remote> list = scoreBoardMap.get(quiz.getId());
            int currentHighest = ((Quiz) list.get(0)).getScore();
            if (quiz.getScore() >= currentHighest) {
                result = true;
            }
        }
        return result;
    }
}
