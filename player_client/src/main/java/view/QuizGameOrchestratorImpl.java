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
        boolean initialized = true;


        Views views = new ViewsImpl();

        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        while (userInput == null || !userInput.equals("EXIT")) {

            message = quizGameOrchestrator.getWelcomeMessage();
            System.out.println(message);

            if (initialized) {
                player = makePlayer(scanner, player);
                initialized = false;
            }
            message = quizGameOrchestrator.printListOfQuizzes();

            while (message.equals(quizGameOrchestrator.getQuizNumberSelectMessage())) {
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.checkForValidNumber(userInput);

                while (message.equals(quizGameOrchestrator.getValidNumberMessage())) {
                    quiz = quizGameOrchestrator.getQuiz();
                    message = quizGameOrchestrator.play(quiz, scanner, player);

                    while (message.equals(quizGameOrchestrator.getUserHighScoreMessage())) {
                        message = quizGameOrchestrator.checkForHighScore(player, quiz, server);

                        try {
                            if (message.equals(quizGameOrchestrator.getNewWinnerMessage(player))) {
                                System.out.println(message);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        message = quizGameOrchestrator.getThanksForPlayingMessage();
                        System.out.println(message);
                        message = quizGameOrchestrator.getStartMessage();
                    }
                }
            }
        }




    }

    private static Player makePlayer(Scanner scanner, Player player) {
        String message1 = null;
        String name = null;
        String country = null;
        String age = null;
        int age1 = 0;
        message1 = "start";

        while (message1 == "start") {
            System.out.println("Please enter your name!");
            name = scanner.nextLine();

            if (checkForNull(name)) {
                message1 = "Please enter your country!";
            }

            while (message1.equals("Please enter your country!")) {
                System.out.println("Please enter your country!");
                country = scanner.nextLine();

                if (checkForNull(country)) {
                    message1 = "Please enter your age!";
                }

                while (message1.equals("Please enter your age!")) {
                    System.out.println("Please enter your age!");
                    age = scanner.nextLine();

                    age1 = 0;
                    try {
                        age1 = Integer.parseInt(age);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(ExceptionMessages.NO_NUMBER_ENTERED);
                    }
                }
            }
        }
        try {
            player = quizPlayerOrchestrator.addPlayer(name, country, age1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return player;
    }

    private static boolean checkForNull(String userInput) {
        boolean result = true;

        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            result = false;
        }
        return result;
    }

    @Override
    public String printListOfQuizzes() {
        List<Quiz> quizList = null;
        try {
            quizList = quizPlayerOrchestrator.getQuizzes();
        } catch (IllegalGameException e) {
            System.out.println(e.getMessage());
        }

        quizMenu = new QuizMenuImpl(quizList);

        try {
            quizMenu.printListOfQuizzes();
            message = getQuizNumberSelectMessage();
        } catch (RemoteException e) {
            e.getMessage();
        }
        return message;
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
            message = getWelcomeMessage();
        }
        return message;
    }

    @Override
    public String getWelcomeMessage() {
        return "\t\t\t♬ ☆ ☆ ☆ Welcome to the Quiz Game! ☆ ☆ ☆ ♬\n";
    }

    @Override
    public String getQuizNumberSelectMessage() {
        return "❤ Please enter the quiz number you want to play! ❤ ";
    }

    @Override
    public String getStartMessage() {
        return "Game start";
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
        return "Thank you for playing, come back soon!\n\n";
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
    public void setQuizNumber(int quizIndex) {
        this.quizIndex = quizIndex;
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
