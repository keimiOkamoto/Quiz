package view;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import constants.ExceptionMessages;
import constants.PlayerMessages;
import controllers.*;
import exceptions.IllegalGameException;
import models.HighScore;
import models.Player;
import models.Quiz;
import modules.QuizPlayerOrchestratorModule;
import modules.ServerModule;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class PlayerStartImpl implements PlayerStart {

    private static QuizPlayerOrchestrator quizPlayerOrchestrator;
    private static PlayerStart playerStart;
    private static String message;
    private int quizIndex;
    private int answerSize;


    public static void main(String[] args) {
        boolean initialized = true;
        Scanner scanner = new Scanner(System.in);
        Player player = null;

        Injector injector = Guice.createInjector(new QuizPlayerOrchestratorModule());
        quizPlayerOrchestrator = injector.getInstance(QuizPlayerOrchestratorImpl.class);

        Injector injector1 = Guice.createInjector(new ServerModule());
        Server server = injector1.getInstance(ServerImpl.class);

        playerStart = new PlayerStartImpl();

        String userInput = null;

        System.out.println(PlayerMessages.WELCOME_MESSAGE);
        while (userInput == null || !userInput.equals(PlayerMessages.EXIT_MESSAGE)) {

            message = PlayerMessages.WELCOME_MESSAGE;

            if (initialized) {
                player = playerStart.makePlayer(scanner, player);
                initialized = false;
            }

            while (message.equals(PlayerMessages.WELCOME_MESSAGE)) {
                System.out.println(PlayerMessages.GET_MENU_OPTION_MESSAGE);
                userInput = scanner.nextLine();
                if (userInput.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);
                message = playerStart.getStartChoice(userInput);

                while (message.equals(PlayerMessages.VIEW_WINNER_MESSAGE)) {
                    message = playerStart.checkIfClosedQuizIsNull();

                    if (message.equals(PlayerMessages.VALID_MESSAGE)) {
                        System.out.println(PlayerMessages.VIEW_WINNER_MESSAGE);
                        message = playerStart.selectClosedQuiz(scanner, message);
                    }
                }

                while (message.equals(PlayerMessages.QUIZ_SELECT_MESSAGE)) {
                    PlayQuiz playQuiz = new PlayQuizImpl(playerStart, quizPlayerOrchestrator);
                    try {
                        message = playQuiz.getQuizMenu(scanner, player, server, message);
                    } catch (RemoteException | IllegalGameException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String selectClosedQuiz(Scanner scanner, String message) {

        List<Quiz> quizList;
        String userInput;
        try {
            quizList = quizPlayerOrchestrator.getClosedQuizList();
            QuizMenu quizMenu1 = new QuizMenuImpl(quizList);
            quizMenu1.print();
            answerSize = quizList.size();

            userInput = scanner.nextLine();
            if(userInput.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);
            if (validInput(userInput)) {
                Quiz quiz1 = quizList.get(Integer.parseInt(userInput) - 1);
                HighScore highScore;
                try {
                    highScore = quizPlayerOrchestrator.getWinner(quiz1.getId());
                    System.out.println(PlayerMessages.WINNER_MESSAGE + highScore.getPlayerName() + PlayerMessages.FROM_MESSAGE + highScore.getPlayerCountry() + PlayerMessages.AGE_MESSAGE + highScore.getPlayerAge());
                } catch (NullPointerException e) {
                    System.out.println(PlayerMessages.QUIZ_NEVER_PLAYED_MESSAGE);
                }

            } else {
                message = PlayerMessages.VIEW_WINNER_MESSAGE;
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Player makePlayer(Scanner scanner, Player player) {
        PlayerCreator playerCreator = new PlayerCreatorImpl();

        return playerCreator.createPlayer(player, scanner, quizPlayerOrchestrator);
    }

    @Override
    public String checkIfClosedQuizIsNull() {
        List<Quiz> quizList;
        try {
            quizList = quizPlayerOrchestrator.getClosedQuizList();
            if (quizList.isEmpty()) {
                System.out.println(PlayerMessages.NO_CLOSED_QUIZ_MESSAGE);
                message = PlayerMessages.WELCOME_MESSAGE;
            } else {
                message = PlayerMessages.VALID_MESSAGE;
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
            message = PlayerMessages.WELCOME_MESSAGE;
        } else if (userInput.equals(PlayerMessages.ONE_MESSAGE)) {
            message = PlayerMessages.QUIZ_SELECT_MESSAGE;
        } else if (userInput.equals(PlayerMessages.TWO_MESSAGE)) {
            message = PlayerMessages.VIEW_WINNER_MESSAGE;
        } else {
            message = PlayerMessages.WELCOME_MESSAGE;
        }
        return message;
    }

    @Override
    public String checkForHighScore(Player player, Quiz quiz, Server server) {

        try {
            if (server.checkForHighScore(quiz, player) && player.getScore() != 0) {
                server.setPlayerAsWinner(player, quiz, player.getScore());
                message = getNewWinnerMessage(player);

            } else {
                message = PlayerMessages.THANKS_FOR_PLAYING;
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

    private String getNewWinnerMessage(Player player) throws RemoteException {
        return PlayerMessages.CONGRATS_MESSAGE + player.getName() + PlayerMessages.FROM_MESSAGE + player.getCountry() + PlayerMessages.STAR_DECORATION;
    }

    private static boolean checkForNull(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }

    private boolean checkIfNumber(String userInput) {
        boolean answer = true;
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            answer = false;
        }
        return answer;
    }

    private boolean validInput(String userInput) {
        return (!checkForNull(userInput)) &&
                checkIfNumber(userInput) &&
                validRangeAnswerSize(Integer.parseInt(userInput));
    }

    private boolean validRangeAnswerSize(int index) {
        return index <= answerSize && index > 0;
    }
}
