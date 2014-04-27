package view;

import constants.ExceptionMessages;
import constants.PlayerMessages;
import controllers.QuizPlayerOrchestrator;
import controllers.Server;
import exceptions.IllegalGameException;
import models.Answer;
import models.Player;
import models.Question;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PlayQuizImpl implements PlayQuiz {

    private PlayerStart playerStart;
    private QuizPlayerOrchestrator quizPlayerOrchestrator;
    private int quizSize;
    private int answerSize;
    private int answerIndex;

    public PlayQuizImpl(PlayerStart playerStart, QuizPlayerOrchestrator quizPlayerOrchestrator) {
        this.playerStart = playerStart;
        this.quizPlayerOrchestrator = quizPlayerOrchestrator;
    }

    @Override
    public String getQuizMenu(Scanner scanner, Player player, Server server, String message) throws RemoteException, IllegalGameException {

        List<Quiz> quizList = quizPlayerOrchestrator.getQuizzes();
        if (quizList == null || quizList.isEmpty()) {
            System.out.println(getExitMessage() + "\n");
            message = PlayerMessages.GET_MENU_OPTION_MESSAGE;

        } else {
            System.out.println(message);
            setQuizSize(quizList.size());
            QuizMenu quizMenu = new QuizMenuImpl(quizList);
            quizMenu.print();

            String userInput = scanner.nextLine();
            if (userInput.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);

            message = checkForValidNumber(userInput, message);

            while (message.equals(getValidNumberMessage())) {
                message = playQuiz(scanner, player, server, playerStart.getQuiz());
            }
        }
        return message;
    }

    private String playQuiz(Scanner scanner, Player player, Server server, Quiz quiz) {
        String message = play(quiz, scanner, player);

        while (message.equals(getUserHighScoreMessage())) {
            message = getUserHighScoreMessage(player, server, quiz);
        }
        return message;
    }


    private String getUserHighScoreMessage(Player player, Server server, Quiz quiz) {
        String message = playerStart.checkForHighScore(player, quiz, server);

        try {
            if (message.equals(getNewWinnerMessage(player))) {
                System.out.println(message);
                quizPlayerOrchestrator.setPlayerAsWinner(player, quiz);
                message = PlayerMessages.THANKS_FOR_PLAYING;
                System.out.println(message);
                quizPlayerOrchestrator.resetPlayerScore(player);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        message = getStartMessage();

        return message;
    }

    private String play(Quiz quiz, Scanner scanner, Player player) {
        Set<Question> questionSet = null;
        String message = null;

        try {
            questionSet = quiz.getQuestions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (Question question : questionSet) {
            String userInput = PlayerMessages.EMPTY_STRING;
            while (!validInput(userInput)) {
                try {
                    System.out.println(PlayerMessages.QUESTION_MESSAGE + question.getQuestion());
                    Set<Answer> answerSet = question.getAnswers();

                    Answer[] answers = answerSet.toArray(new Answer[answerSet.size()]);
                    setAnswerSize(answers.length);

                    for (int y = 0; y < answers.length; y++) {
                        System.out.println((y + 1) + PlayerMessages.COLON_MESSAGE + answers[y].getAnswer());
                    }
                    userInput = scanner.nextLine();
                    if (userInput.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);

                    if (validInput(userInput)) {
                        setAnswerIndex(userInput);

                        if (answers[getAnswerIndex() - 1].getAnswerType()) {
                            player.incrementScore();
                        }
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            System.out.println(PlayerMessages.PLAYER_SCORE_MESSAGE + player.getScore() + PlayerMessages.OUT_OF_MESSAGE + questionSet.size() + PlayerMessages.MUSIC_DECORATION);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        message = getUserHighScoreMessage();

        return message;
    }

    private boolean validRangeQuizSize(int index) {
        boolean result = true;
        if (index > quizSize || index <= 0) {
            result = false;
        }
        return result;
    }

    private boolean validInput(String userInput) {
        return (!checkForNull(userInput)) &&
                checkIfNumber(userInput) &&
                validRangeAnswerSize(Integer.parseInt(userInput));
    }

    private boolean validRangeAnswerSize(int index) {
        return index <= answerSize && index > 0;
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

    private static boolean checkForNull(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }

    private int getAnswerIndex() {
        return answerIndex;
    }

    private void setAnswerIndex(String answerIndex) {
        this.answerIndex = Integer.parseInt(answerIndex);
    }

    private void setAnswerSize(int answerSize) {
        this.answerSize = answerSize;
    }

    private void setQuizSize(int size) {
        this.quizSize = size;
    }

    private String getStartMessage() {
        return "Game start";
    }

    private String getNewWinnerMessage(Player player) throws RemoteException {
        return PlayerMessages.CONGRATS_MESSAGE + player.getName() + PlayerMessages.FROM_MESSAGE + player.getCountry() + PlayerMessages.STAR_DECORATION;
    }

    private String getValidNumberMessage() {
        return PlayerMessages.NUMBER_VALID_MESSAGE;
    }

    private String getExitMessage() {
        return PlayerMessages.NO_QUIZZES_MESSAGE;
    }

    public String getUserHighScoreMessage() {
        return PlayerMessages.IS_HIGH_SCORE_MESSAGE;
    }

    private String checkForValidNumber(String userInput, String message) {
        int index;
        try {
            index = Integer.parseInt(userInput);
            playerStart.setQuizNumber(index);

            if (validRangeQuizSize(index)) {
                message = getValidNumberMessage();
            } else {
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            }
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
        }
        return message;
    }
}
