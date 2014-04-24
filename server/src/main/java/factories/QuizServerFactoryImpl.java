package factories;

import com.google.inject.Inject;
import controllers.ClosedQuizContainer;
import controllers.QuizContainer;
import controllers.QuizServer;
import controllers.QuizServerImpl;
import models.ScoreKeeper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuizServerFactoryImpl extends UnicastRemoteObject implements QuizServerFactory {

    private ItemsFactory itemsFactory;
    private PlayerFactory playerFactory;
    private QuizContainer quizContainer;
    private ScoreKeeper scoreKeeper;
    private ClosedQuizContainer closedQuizContainer;

    @Inject
    public QuizServerFactoryImpl(ItemsFactory itemsFactory, PlayerFactory playerFactory, QuizContainer quizContainer, ScoreKeeper scoreKeeper, ClosedQuizContainer closedQuizContainer) throws RemoteException {
        this.itemsFactory = itemsFactory;
        this.playerFactory = playerFactory;
        this.quizContainer = quizContainer;
        this.scoreKeeper = scoreKeeper;
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public QuizServer getQuizServer() throws RemoteException {
        return new QuizServerImpl(itemsFactory, playerFactory, quizContainer, scoreKeeper, closedQuizContainer);
    }
}
