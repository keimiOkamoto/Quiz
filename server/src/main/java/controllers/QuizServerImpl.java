package controllers;

import factories.ItemsFactory;
import factories.PlayerFactory;
import models.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuizServerImpl extends UnicastRemoteObject implements QuizServer {

    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;
    private ScoreKeeper scoreKeeper;
    private ClosedQuizContainer closedQuizContainer;
    private PlayerFactory playerFactory;
    private Quiz quiz;
    private Question question;

    public QuizServerImpl(ItemsFactory itemsFactory, PlayerFactory playerFactory, QuizContainer quizContainer, ScoreKeeper scoreKeeper, ClosedQuizContainer closedQuizContainer) throws RemoteException {
        this.itemsFactory = itemsFactory;
        this.playerFactory = playerFactory;
        this.quizContainer = quizContainer;
        this.scoreKeeper = scoreKeeper;
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public synchronized boolean titleIsValid(String title) throws RemoteException {
        return !quizContainer.contains(title);
    }

    @Override
    public synchronized boolean iDIsValid(int id) throws RemoteException {
        return quizContainer.contains(id);
    }

    @Override
    public synchronized void save() throws RemoteException {
        quizContainer.save(quiz);
    }

    @Override
    public synchronized void endQuiz(int id) throws RemoteException {
        quizContainer.closeQuizWith(id);
    }

    @Override
    public synchronized ItemsFactory getItemsFactory() throws RemoteException {
        return itemsFactory;
    }

    @Override
    public synchronized int generateQuiz(String title) throws RemoteException {
        quiz = itemsFactory.generateQuiz(title);
        return quiz.getId();
    }

    @Override
    public synchronized Question generateQuestion(String question) throws RemoteException {
        return itemsFactory.generateQuestion(question);
    }

    @Override
    public synchronized Answer generateAnswer(String answer, boolean answerType) throws RemoteException {
        return itemsFactory.generateAnswer(answer, answerType);
    }

    /********** Player client methods ***********/

    @Override
    public synchronized List<Quiz> getQuizzes() throws RemoteException {
        return quizContainer.getQuizzes();
    }

    @Override
    public synchronized Quiz getQuiz(int id) throws RemoteException {
        return quizContainer.getQuizBy(id);
    }

    @Override
    public synchronized boolean checkForHighScore(Quiz quiz, Player player)throws RemoteException {
        return scoreKeeper.scoreIsHighest(player, quiz);
    }

    @Override
    public synchronized PlayerFactory getPlayerFactory()throws RemoteException {
        return playerFactory;
    }

    @Override
    public synchronized void setPlayerAsWinner(Quiz quiz, Player player) throws RemoteException {
        scoreKeeper.setLeader(quiz, player);
    }

    @Override
    public synchronized Player getWinnerBy(int quizId)throws RemoteException  {
        return scoreKeeper.getLeader(quizId);
    }

    @Override
    public synchronized Player generatePlayer(String name, String country, int age) throws RemoteException {
        return playerFactory.generatePlayer(name, country, age);
    }

    @Override
    public synchronized void resetPlayerScore(Player player) throws RemoteException {
        player.resetScore();
    }

    @Override
    public synchronized List<Quiz> getClosedQuizList() throws RemoteException {
        return quizContainer.getClosedQuizList();
    }

    @Override
    public boolean isQuizNull() throws RemoteException {
        return quiz == null;
    }

    @Override
    public boolean quizContains(String questionStr) throws RemoteException {
        return quiz.contains(questionStr);
    }

    @Override
    public void addQuestionToQuiz(String questionStr) throws RemoteException {
        question = generateQuestion(questionStr);
        quiz.addQuestion(question);
    }

    @Override
    public boolean isQuestionNull() throws RemoteException {
        return question == null;
    }

    @Override
    public boolean questionContains(String answer) throws RemoteException {
        return question.contains(answer);
    }

    @Override
    public void addToQuestion(String answer, boolean answerType) throws RemoteException {
        question.add(generateAnswer(answer, answerType));
    }

    @Override
    public boolean isQuizEmpty() throws RemoteException {
        return quiz.isEmpty();
    }
}
