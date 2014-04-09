package views;

public class SetupViewImpl implements SetupView {

    @Override
    public void startMessage() {
        System.out.println("❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n");
        System.out.println("What would you like to do?");
        System.out.println("1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.");

    }
}
