package models;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import factories.ItemsFactory;
import utils.DiskWriter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ScoreKeeperImpl extends UnicastRemoteObject implements ScoreKeeper {
    private static final long serialVersionUID = -4812254102171441893L;
    private Map<Integer, HighScore> scoreBoardMap;
    private ItemsFactory itemsFactory;

    @Inject
    protected ScoreKeeperImpl(ItemsFactory itemsFactory, DiskWriter diskWriter) throws RemoteException {
        if (diskWriter.checkIfHighScoreDataExists()) {
            scoreBoardMap = diskWriter.getHighScoreBoardMap();
        } else {
            scoreBoardMap = new HashMap<>();
        }
        this.itemsFactory = itemsFactory;
        diskWriter.persist(scoreBoardMap);
    }

    @Override
    public void addHighScore(Quiz quiz, Player player) throws RemoteException {
        if (!highScoreContains(quiz) || scoreIsHighest(player, quiz)) {
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
    public int getHighScore(Quiz quiz) throws RemoteException {
        return scoreBoardMap.get(quiz.getId()).getHighScore();
    }

    @Override
    public Player getLeader(int quizId) {
        HighScore highScore = scoreBoardMap.get(quizId);
        return highScore.getPlayer();
    }

    @Override
    public void setLeader(Quiz quiz, Player player) throws RemoteException {
            HighScore highScore = itemsFactory.generateHighScore(quiz, player);
            scoreBoardMap.put(quiz.getId(), highScore);
    }

    @Override
    public boolean scoreIsHighest(Player player, Quiz quiz) throws RemoteException {
        boolean result = true;
        int id = quiz.getId();
        if (scoreBoardMap.containsKey(id)) {
            try {
                HighScore highScore = scoreBoardMap.get(quiz.getId());
                int currentHighest = highScore.getHighScore();

                result = player.getScore() >= currentHighest;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
