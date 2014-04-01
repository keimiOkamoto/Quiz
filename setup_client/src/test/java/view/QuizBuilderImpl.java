package view;

import java.util.Scanner;

public class QuizBuilderImpl implements QuizBuilder {

    @Override
    public void launch() {
        System.out.println("Welcome to the quiz creator. Would you like to create a quiz?\nPlease press Y to continue or N to exit.");

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String a = scanner.nextLine();
            if (a.equals("Y")) {
                play();
            }
        }
    }

    private void play() {

    }
}
