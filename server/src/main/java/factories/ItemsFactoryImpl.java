package factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.*;
import utils.UniqueNumberGeneratorUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Singleton
public class ItemsFactoryImpl extends UnicastRemoteObject implements ItemsFactory {
    private UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils;

    @Inject
    public ItemsFactoryImpl(UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils) throws RemoteException {
        this.uniqueNumberGeneratorUtils = uniqueNumberGeneratorUtils;
    }

    @Override
    public Quiz generateQuiz(String title) throws RemoteException {
        return new QuizImpl(uniqueNumberGeneratorUtils.getUniqueNumber(), title);
    }

    @Override
    public Question generateQuestion(String question) throws RemoteException {
        return new QuestionImpl(question);
    }

    @Override
    public Answer generateAnswer(String answer, boolean answerType) throws RemoteException {
        return new AnswerImpl(answer, answerType);
    }

    @Override
    public void flush() throws RemoteException {
        uniqueNumberGeneratorUtils.flush();
    }

    @Override
    public HighScore getHighScore(Quiz quiz, Player player) throws RemoteException{
        return new HighScoreImpl(quiz, player);
    }
}
