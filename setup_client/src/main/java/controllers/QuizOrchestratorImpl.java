package controllers;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import items.Answer;
import items.Question;
import items.Quiz;

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
    public int createQuiz(String title) throws IllegalArgumentException, IllegalQuizException {
        if (title == null || title.trim().equals("")) throw new IllegalArgumentException("Title is empty. Please enter a title with at least one character.");
        if (!server.valid(title)) throw new IllegalQuizException("A quiz with the same name already exists. Please try again with another name.");
        quiz = server.createQuiz(title.trim());
        return quiz.getId();
    }

    @Override
    public void addQuestion(String questionString) throws IllegalQuizException, IllegalArgumentException {
        if (quiz == null) throw new IllegalQuizException("Quiz does not exist. Please create a quiz and try again.");
        if (questionString == null || questionString.trim().equals("")) throw new IllegalArgumentException("Question entered is empty. Please try again.");
        if (!quiz.contains(questionString)) throw new IllegalQuizException("You have already entered that question. Please enter a different one.");
        question = server.createQuestion(questionString);
        quiz.addQuestion(question);
    }

    @Override
    public void addAnswer(String answer) throws IllegalQuestionException, IllegalArgumentException {
        if (question == null) throw new IllegalQuestionException("Question doesn't exist. There must be a question to have an answer!");
        if (!question.contains(answer)) throw new IllegalQuestionException("You have already entered that answer. Please enter a different one.");
        if (answer == null || answer.trim().equals("")) throw new IllegalArgumentException("Answer entered is empty. Please enter a contains answer.");
        Answer answer1 = server.createAnswer(answer);
        question.add(answer1);
    }

    @Override
    public void closeQuiz(int id) throws IllegalQuizException {
        if (!server.valid(id)) throw new IllegalQuizException("A quiz with that ID does not exist. Please enter a contains ID.");
        server.closeQuiz(id);
    }

    @Override
    public void save(Quiz quiz) throws IllegalQuizException {
        if (quiz == null) throw new IllegalQuizException("There is no quiz to save to server.");
        server.save(quiz);
    }

    @Override
    public String getTitle() {
        return quiz.getTitle();
    }
}
