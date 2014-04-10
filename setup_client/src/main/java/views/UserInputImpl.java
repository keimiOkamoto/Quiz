package views;

import java.util.Scanner;

public class UserInputImpl implements UserInput {

    @Override
    public String type() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
