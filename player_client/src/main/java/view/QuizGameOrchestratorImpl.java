package view;

import constants.ExceptionMessages;
import controllers.*;
import exceptions.IllegalGameException;
import models.Answer;
import models.Player;
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
    private Answer[] answers;
    private static Quiz quiz;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        boolean initialized = false;


        Views views = new ViewsImpl();

        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        while (userInput == null || !userInput.equals("EXIT")) {

            if (initialized) {
                player = makePlayer(scanner, player);
            }

            if (message.equals(quizGameOrchestrator.getStartMessage())) {
                printWelcomeAndListOfQuizzes();
            }

            while (message.equals(quizMenu.getQuizNumberMessage())) {
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.checkForValidNumber(userInput);
            }

            while (message.equals(quizGameOrchestrator.getValidNumberMessage())) {
                quiz = quizGameOrchestrator.getQuiz();
                message = quizGameOrchestrator.play(quiz, scanner, player);

                while (message.equals(quizGameOrchestrator.getUserHighScoreMessage())) {
                    quizGameOrchestrator.checkForHighScore(player, quiz, server);
                }
            }

            while (message.equals(quizGameOrchestrator.getUserHighScoreMessage())) {
                System.out.println(message);
                message = quizGameOrchestrator.getThanksForPlayingMessage();
            }

            while (message.equals(quizGameOrchestrator.getUserHighScoreMessage())) {
                System.out.println(message);
                message = quizGameOrchestrator.getStartMessage();
            }
        }
    }

    private static Player makePlayer(Scanner scanner, Player player) {
        System.out.println("Please enter your name!");
        String name = scanner.nextLine();

        System.out.println("Please enter your country!");
        String country = scanner.nextLine();

        System.out.println("Please enter your age!");
        String age = scanner.nextLine();
        int age1 = Integer.parseInt(age);


        try {
            player = quizPlayerOrchestrator.addPlayer(name, country, age1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return player;
    }

    private static void printWelcomeAndListOfQuizzes() {
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
    public String getUserHighScoreMessage() {
        return "is highScore";
    }

    @Override
    public String getNewWinnerMessage(Player player) throws RemoteException {
        return "\t\t\tHIGH-SCORE!\nCongratulations " + player.getName() + " from " + player.getCountry() + "!";
    }

    @Override
    public String getThanksForPlayingMessage() {
        return "Thank you for playing!\n\t\tCome back soon!";
    }

    @Override
    public void setQuizNumber(int quizIndex) {
        this.quizIndex = quizIndex;
    }

    @Override
    public String play(Quiz quiz, Scanner scanner, Player player) {
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

                answers = answerSet.toArray(new Answer[answerSet.size()]);

                for (int y = 0; y < answers.length; y++) {
                    System.out.println((y + 1) + ": " + answers[y].getAnswer());
                }
                String userInput = scanner.nextLine();
                int answerIndex = Integer.parseInt(userInput);

                if (answers[answerIndex - 1].getAnswerType()) {
                    player.incrementScore();
                    System.out.println("score is " + player.getScore());
                }

                message = getUserHighScoreMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("Total Score is: " + player.getScore());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }


    @Override
    public String checkForHighScore(Player player, Quiz quiz, Server server) {
        try {
            if (server.checkForHighScore(quiz, player)) {
                server.setPlayerAsWinner(player, quiz, player.getScore());
                message = getNewWinnerMessage(player);

            } else {
                message = getThanksForPlayingMessage();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String getStartMessage() {
        return null;
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
