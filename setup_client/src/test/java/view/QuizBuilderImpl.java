package view;

import java.util.Scanner;

public class QuizBuilderImpl implements QuizBuilder {
    private Scanner scanner;

    @Override
    public void launch() {
        System.out.println("Welcome to the quiz creator. Would you like to create a quiz?\nPlease press Y to continue or N to exit.");
        scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("Y")) {
            play();
        }
    }

    private void play() {

    }
}
