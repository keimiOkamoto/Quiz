package views;

import java.util.Scanner;

public interface QuestionView {


    /**
     * When the message equals Save question this method
     * is called. This will get the next message depending
     * on the users input and choice.
     *
     * @param scanner A scanner object for the user input
     * @param setupOrchestrator A setupOrchestrator
     * @param message A message that determines the current state the user is in.
     * @param views A views object.
     * @return A message that determines the next stage of the quiz setup proccess.
     */
    String getUserQuestionMessage(Scanner scanner, SetupOrchestrator setupOrchestrator, String message, Views views);
}
