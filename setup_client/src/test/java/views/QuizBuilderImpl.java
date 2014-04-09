package views;

import java.util.Scanner;

public class QuizBuilderImpl implements QuizBuilder {

    @Override
    public void launch() {
        System.out.println("Welcome to the quiz creator. Would you like to create a quiz?\nPlease press 'Y' to continue or 'EXIT' to exit.");

        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        while (!((a.equals("EXIT")))){
            if (scanner.hasNext() && a.equals("Y")) {
                play();
            }
        }
    }

    private void play() {
        System.out.println("Hi");
    }
}
