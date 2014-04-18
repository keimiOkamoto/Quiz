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
    private int quizSize;
    private int answerSize;
    private int answerIndex;


    public static void main(String[] args) {
        boolean initialized = true;
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        Views views = new ViewsImpl();
        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);

        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        System.out.println(quizGameOrchestrator.getWelcomeMessage());
        while (userInput == null || !userInput.equals("EXIT")) {

            message = quizGameOrchestrator.getWelcomeMessage();

            if (initialized) {
                player = quizGameOrchestrator.makePlayer(scanner, player);
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
    public String printListOfQuizzes() {

        System.out.println("☆ Please select which quiz you would like to play by typing in the index number. ☆");
        List<Quiz> quizList = null;
        try {
            quizList = quizPlayerOrchestrator.getQuizzes();
            setQuizSize(quizList.size());
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
    public void setQuizSize(int size) {
        this.quizSize = size;
    }

    @Override
    public int getQuizSize() {
        return quizSize;
    }

    @Override
    public String checkForValidNumber(String userInput) {
        int index;
        try {
            index = Integer.parseInt(userInput);
            quizGameOrchestrator.setQuizNumber(index);

            if (validRangeQuizSize(index)) {
                message = quizGameOrchestrator.getValidNumberMessage();
            } else {
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
                message = getWelcomeMessage();
            }
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = getWelcomeMessage();
        }
        return message;
    }

    private boolean validRangeQuizSize(int index) {
        boolean result = true;
        if (index > quizSize || index <= 0) {
            result = false;
        }
        return result;
    }

    private boolean validRangeAnswerSize(int index) {
        return index <= answerSize && index > 0;
    }

    @Override
    public String getWelcomeMessage() {
        return "\t\t\t♬ ☆ ☆ ☆ Welcome to the Quiz Game! ☆ ☆ ☆ ♬\n";
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

    @Override
    public String getQuizNumberSelectMessage() {
        return "☆ Please enter the quiz number you want to play! ☆ ";
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
        return "\t\tYOU GOT THE HIGHEST SCORE!\n☆☆☆☆☆ Congratulations " + player.getName() + " from " + player.getCountry() + "!  ☆☆☆☆☆";
    }

    @Override
    public String getThanksForPlayingMessage() {
        return "♬ ☆ Thank you for playing, come back soon! ☆ ♬\n";
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
            String userInput = "";

            while (!validInput(userInput)) {
                try {
                    System.out.println("\nQUESTION: " + question.getQuestion());
                    Set<Answer> answerSet = question.getAnswers();

                    answers = answerSet.toArray(new Answer[answerSet.size()]);
                    setAnswerSize(answers.length);

                    for (int y = 0; y < answers.length; y++) {
                        System.out.println((y + 1) + ": " + answers[y].getAnswer());
                    }
                    userInput = scanner.nextLine();

                    if(validInput(userInput)) {
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
            System.out.println("¸¸.•*¨*•♫♪ You scored: " + player.getScore() + " out of " + getQuizSize() + "! ♪♫•*¨*•.¸¸");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        message = getUserHighScoreMessage();

        return message;
    }

    private boolean validInput(String userInput) {
        return (!checkForNull(userInput)) &&
                checkIfNumber(userInput) &&
                validRangeAnswerSize(Integer.parseInt(userInput));
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


    private void setAnswerIndex(String answerIndex) {
        this.answerIndex = Integer.parseInt(answerIndex);
    }

    private int getAnswerIndex() {
        return answerIndex;
    }

    private void setAnswerSize(int answerSize) {
        this.answerSize = answerSize;
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
