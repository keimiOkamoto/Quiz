package view;

import constants.ExceptionMessages;
import controllers.*;
import exceptions.IllegalGameException;
import models.Answer;
import models.Question;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class QuizGameOrchestratorImpl implements QuizGameOrchestrator {

    private static QuizPlayerOrchestrator quizPlayerOrchestrator;
    private static QuizGameOrchestrator quizGameOrchestrator;
    private static String message;
    private static QuizMenu quizMenu;
    private int quizIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Views views = new ViewsImpl();

        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        while (userInput == null || !userInput.equals("EXIT")) {

            List<Quiz> quizList = null;
            try {
                quizList = quizPlayerOrchestrator.getQuizzes();
            } catch (IllegalGameException e) {
                System.out.println(e.getMessage());
            }

            quizMenu = new QuizMenuImpl(quizList);
            message = quizMenu.getQuizNumberMessage();

            System.out.println(message);
            try {
                quizMenu.printListOfQuizzes();
            } catch (RemoteException e) {
                e.getMessage();
            }

            while (message.equals(quizMenu.getQuizNumberMessage())) {
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.checkForValidNumber(userInput);
            }

            while (message.equals(quizGameOrchestrator.getValidNumberMessage())) {
                Quiz quiz = quizGameOrchestrator.getQuiz();
                message = quizGameOrchestrator.play(quiz);
            }

            while (message.equals(quizGameOrchestrator.getUserAnswerMessage())) {
                userInput = scanner.nextLine();
                quizGameOrchestrator.checkForValidInputForAnswer();

            }
        }
    }

    @Override
    public String checkForValidNumber(String userInput) {
        int index = 0;
        try {
            index = Integer.parseInt(userInput);
            quizGameOrchestrator.setQuizNumber(index);
            message = quizGameOrchestrator.getValidNumberMessage();

        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.NO_NUMBER_ENTERED);
            message = quizMenu.getQuizNumberMessage();
        }
        return message;
    }

    @Override
    public String getValidNumberMessage() {
        return "Number valid.";
    }

    @Override
    public String getUserAnswerMessage() {
        return "Please select from above answers by entering the index number.";
    }

    @Override
    public void setQuizNumber(int quizIndex) {
        this.quizIndex = quizIndex;
    }

    @Override
    public String play(Quiz quiz) {
        Set<Question> questionSet = null;
        try {
            questionSet = quiz.getQuestions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (Question question : questionSet) {
            try {
                System.out.println("QUESTION:" + question.getQuestion());
                Set<Answer> answerSet = question.getAnswers();

                Answer[] answers = answerSet.toArray(new Answer[answerSet.size()]);

                for (int y = 0; y < answers.length; y++) {
                    System.out.println((y + 1) + ": " + answers[y].getAnswer());
                }
                message = getUserAnswerMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    @Override
    public void checkForValidInputForAnswer() {

    }

    @Override
    public int getQuizIndex() {
        return quizIndex;
    }

    public Quiz getQuiz() {
        List<Quiz> quizList;
        Quiz quiz = null;
        try {
            quizList = quizPlayerOrchestrator.getQuizzes();
            quiz = quizList.get(getQuizIndex() - 1);
        } catch (IllegalGameException e) {
            e.printStackTrace();
        }
        return quiz;
    }
}
