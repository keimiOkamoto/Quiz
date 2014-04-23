package controllers;

import constants.ExceptionMessages;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import models.Answer;
import models.Question;
import models.Quiz;

import java.rmi.RemoteException;

/**
 * Controls the organization of the quiz.
 */
public class QuizOrchestratorImpl implements QuizOrchestrator {
    private Server server;
    private String title;

    public QuizOrchestratorImpl(Server server) {
        this.server = server;
    }

    @Override
    public int createQuiz(String title) throws IllegalArgumentException, IllegalQuizException, RemoteException {
        if (invalid(title)) throw new IllegalArgumentException(ExceptionMessages.EMPTY_TITLE);
        if (!server.valid(title)) throw new IllegalQuizException(ExceptionMessages.DUPLICATE_QUIZ);
        this.title = title;
        return server.createQuiz(title.trim());
    }

    @Override
    public void addQuestion(String questionStr) throws IllegalArgumentException, IllegalQuizException, RemoteException {
        if (invalid(questionStr)) throw new IllegalArgumentException(ExceptionMessages.EMPTY_QUESTION);
        if (server.isQuizNull()) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_EXISTS);
        if (server.quizContains(questionStr)) throw new IllegalQuizException(ExceptionMessages.DUPLICATE_QUESTION);
        server.addQuestionToQuiz(questionStr);
    }

    @Override
    public void addAnswer(String answer, boolean answerType) throws IllegalQuestionException, IllegalArgumentException, RemoteException {
        if (invalid(answer)) throw new IllegalArgumentException(ExceptionMessages.EMPTY_ANSWER);
        if (server.isQuestionNull()) throw new IllegalQuestionException(ExceptionMessages.NO_QUESTION_EXISTS);
        if (server.questionContains(answer)) throw new IllegalQuestionException(ExceptionMessages.DUPLICATE_ANSWER);
        server.addToQuestion(answer, answerType);
    }

    @Override
    public void save() throws IllegalQuizException, RemoteException {
        if (server.isQuizNull()) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_TO_SAVE);
        if (server.isQuizEmpty()) throw new IllegalQuizException(ExceptionMessages.NO_QUESTIONS_CANNOT_SAVE);
        server.save();
    }

    @Override
    public void closeQuiz(int id) throws IllegalQuizException, RemoteException {
        if (!server.valid(id)) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_WITH_ID_EXISTS);
        server.closeQuiz(id);
    }

    @Override
    public String getTitle() {
        return title;
    }
//
//    @Override
//    public Quiz getQuiz() {
//        return quiz;
//    }

    /*
     * Validates if the input is valid.
     */
    private boolean invalid(String questionStr) {
        return questionStr == null || questionStr.trim().isEmpty();
    }
}
