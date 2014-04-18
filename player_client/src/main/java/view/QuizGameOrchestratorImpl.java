package view;

import constants.ExceptionMessages;
import constants.SetUpMessages;
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
    private int quizSize;
    private int answerSize;
    private int answerIndex;


    public static void main(String[] args) throws IllegalGameException, RemoteException {
        boolean initialized = true;
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);

        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        System.out.println(SetUpMessages.WELCOME_MESSAGE);
        while (userInput == null || !userInput.equals("EXIT")) {

            message = SetUpMessages.WELCOME_MESSAGE;

            if (initialized) {
                player = quizGameOrchestrator.makePlayer(scanner, player);
                initialized = false;
            }

            while (message.equals(SetUpMessages.WELCOME_MESSAGE)) {
                System.out.println(quizGameOrchestrator.getMenuMessage());
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.getStartChoice(userInput);


                while (message.equals(quizGameOrchestrator.getClosedQuizMessage())) {
                    message = quizGameOrchestrator.printListOfClosedQuizzes();

                    if (message.equals(quizGameOrchestrator.getClosedQuizMessage())) {
                        userInput = scanner.nextLine();
                    }
                } //TODO


                //Done
                while (message.equals(SetUpMessages.QUIZ_SELECT_MESSAAGE)) {
                    PlayQuiz playQuiz = new PlayQuizImpl(quizGameOrchestrator, quizPlayerOrchestrator);
                    message = playQuiz.getQuizMenu(scanner, player, server, message);
                }
            }
        }
    }




    @Override
    public Player makePlayer(Scanner scanner, Player player) {
        String message1 = null;
        String name = null;
        String country = null;
        String age = null;
        int age1 = 0;
        message1 = "start";

        while (message1 == "start") {
            System.out.println(getNameMessage());
            name = scanner.nextLine();

            if (!checkForNull(name)) {
                message1 = getCountryMessage();
            } else {
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            }
            while (message1.equals(getCountryMessage())) {
                System.out.println(message1);
                country = scanner.nextLine();

                if (!checkForNull(country)) {
                    message1 = getAgeMessage();
                } else {
                    System.out.println(ExceptionMessages.INVALID_USER_INPUT);
                }

                while (message1.equals(getAgeMessage())) {
                    System.out.println(message1);
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
        return userInput == null || userInput.trim().isEmpty();
    }


    @Override
    public String printListOfClosedQuizzes() {
        List<Quiz> quizList = null;
        try {
            quizList = quizPlayerOrchestrator.getClosedQuizList();
            if (quizList.isEmpty()) {
                System.out.println("No quizzes have been closed!");
                message = SetUpMessages.WELCOME_MESSAGE;

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String getStartChoice(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.WELCOME_MESSAGE;
        } else if (userInput.equals("1")) {
            message = SetUpMessages.QUIZ_SELECT_MESSAAGE;
        } else if (userInput.equals("2")) {
            message = getClosedQuizMessage();
        } else {
            message = SetUpMessages.WELCOME_MESSAGE;
        }
        return message;
    }

//
//    @Override
//    public int getQuizSize() {
//        return quizSize;
//    }










    @Override
    public String checkForHighScore(Player player, Quiz quiz, Server server) {

        try {
            if (server.checkForHighScore(quiz, player) && player.getScore() != 0) {
                server.setPlayerAsWinner(player, quiz, player.getScore());
                message = getNewWinnerMessage(player);

            } else {
                message = SetUpMessages.THANKS_FOR_PLAYING;
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

    @Override
    public String getClosedQuizMessage() {
        return "☆ Please enter the quiz number you want to view the winner of! ☆ ";
    }

//    @Override
//    public String getSelectClosedQuizMessage() {
//        return "Select close quiz";
//    }

    private String getNewWinnerMessage(Player player) throws RemoteException {
        return "\t\tYOU GOT THE HIGHEST SCORE!\n☆☆☆☆☆ Congratulations " + player.getName() + " from " + player.getCountry() + "!  ☆☆☆☆☆";
    }


    @Override
    public String getMenuMessage() {
        return "Press 1: To play a quiz Press 2: To view quizzes that have been closed.";
    }

    @Override
    public String getNameMessage() {
        return "☆ Please enter your name ☆";
    }

    @Override
    public String getCountryMessage() {
        return "☆ Please enter your country ☆";
    }

    @Override
    public String getAgeMessage() {
        return "☆ Please enter your age ☆";
    }
}
