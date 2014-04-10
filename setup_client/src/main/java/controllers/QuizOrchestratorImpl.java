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
    private Quiz quiz;
    private Question question;

    public QuizOrchestratorImpl(Server server) {
        this.server = server;
    }

    @Override
    public int createQuiz(String title) throws IllegalArgumentException, IllegalQuizException, RemoteException {
        if (title == null || title.length() == 0) throw new IllegalArgumentException(ExceptionMessages.EMPTY_TITLE);
        if (!server.valid(title)) throw new IllegalQuizException(ExceptionMessages.DUPLICATE_QUIZ);
        quiz = server.createQuiz(title.trim());
        return quiz.getId();
    }

    @Override
    public void addQuestion(String questionStr) throws IllegalArgumentException, IllegalQuizException, RemoteException {
        if (questionStr == null || questionStr.length() == 0) throw new IllegalArgumentException(ExceptionMessages.EMPTY_QUESTION);
        if (quiz == null) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_EXISTS);
        if (quiz.contains(questionStr)) throw new IllegalQuizException(ExceptionMessages.DUPLICATE_QUESTION);
        question = server.createQuestion(questionStr);
        quiz.addQuestion(question);
    }

    @Override
    public void addAnswer(String answer, boolean answerType) throws IllegalQuestionException, IllegalArgumentException, RemoteException {
        if (answer == null || answer.length() == 0) throw new IllegalArgumentException(ExceptionMessages.EMPTY_ANSWER);
        if (question == null) throw new IllegalQuestionException(ExceptionMessages.NO_QUESTION_EXISTS);
        if (question.contains(answer)) throw new IllegalQuestionException(ExceptionMessages.DUPLICATE_ANSWER);
        Answer answer1 = server.createAnswer(answer, answerType);
        question.add(answer1);
    }

    @Override
    public void closeQuiz(int id) throws IllegalQuizException, RemoteException {
        if (!server.valid(id)) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_WITH_ID_EXISTS);
        server.closeQuiz(id);
    }

    @Override
    public void save(Quiz quiz) throws IllegalQuizException, RemoteException {
        if (quiz == null) throw new IllegalQuizException(ExceptionMessages.NO_QUIZ_TO_SAVE);
        if (quiz.isEmpty()) throw new IllegalQuizException(ExceptionMessages.NO_QUESTIONS_CANNOT_SAVE);
        server.save(quiz);
    }

    @Override
    public String getTitle() {
        return quiz.getTitle();
    }
}
