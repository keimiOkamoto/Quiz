package models;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import factories.ItemsFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ScoreKeeperImpl extends UnicastRemoteObject implements ScoreKeeper {
    private Map<Integer, HighScore> scoreBoardMap = new HashMap<>();
    private ItemsFactory itemsFactory;

    @Inject
    protected ScoreKeeperImpl(ItemsFactory itemsFactory) throws RemoteException {
        this.itemsFactory = itemsFactory;
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
        return scoreBoardMap.get(quizId).getPlayer();
    }

    @Override
    public void setLeader(Quiz quiz, Player player) throws RemoteException {
            HighScore highScore = itemsFactory.getHighScore(quiz, player);
            scoreBoardMap.put(quiz.getId(), highScore);
    }

    @Override
    public boolean scoreIsHighest(Player player, Quiz quiz) throws RemoteException {
        boolean result = true;
        if (scoreBoardMap.containsKey(quiz.getId())) {
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
