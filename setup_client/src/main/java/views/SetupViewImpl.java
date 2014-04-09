package views;

import constants.ExceptionMessages;

import java.util.Scanner;

public class SetupViewImpl implements SetupView {

    @Override
    public void startMessage() {
        System.out.println("❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n");
        System.out.println("What would you like to do?");
        System.out.println("1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.");
        System.out.println("Enter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.");
    }

    @Override
    public void selectOption() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        switch (message) {
            case "1":
                System.out.println(message);
                createQuizOption();
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


    private void createQuizOption() {
        System.out.print("W");
    }

    private void closeAQuizOption() {

    }

    private void exit() {
    }
}
