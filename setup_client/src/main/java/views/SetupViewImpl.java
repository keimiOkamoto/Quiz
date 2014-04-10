package views;

import constants.ExceptionMessages;
import controllers.QuizOrchestrator;
import exceptions.IllegalQuizException;

import java.util.Scanner;

public class SetupViewImpl implements SetupView {
    private final UserInput userInput;
    private QuizOrchestrator quizOrchestrator;

    public SetupViewImpl (QuizOrchestrator quizOrchestrator, UserInput userInput) {
        this.quizOrchestrator = quizOrchestrator;
        this.userInput = userInput;
    }
    @Override
    public void startMessage() {
        System.out.println("❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n");
        System.out.println("What would you like to do?");
        System.out.println("1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.");
        System.out.println("Enter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.");
    }

    @Override
    public void selectOption() throws IllegalArgumentException {
        String message = userInput.type();

        switch (message) {
            case "1":
                createQuizTitle();
                break;
            case "2":
                closeAQuizOption();
                break;
            case "EXIT":
                exit();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_INPUT);
        }
    }

    private void createQuizTitle() {
        System.out.println("Please enter the title of your quiz: ");
        String title = userInput.type();
        enterTitle(title);
    }

    private void enterTitle(String title) {
        try {
            quizOrchestrator.createQuiz(title);
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeAQuizOption() {
        System.out.println("Please enter the ID of the quiz you would like to close.");

    }

    private void exit() {
        System.out.println("System exiting.");
    }

}
