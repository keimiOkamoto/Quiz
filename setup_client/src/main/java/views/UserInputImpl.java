package views;

import controllers.QuizOrchestrator;

import java.util.Scanner;

public class UserInputImpl implements UserInput {
    private QuizOrchestrator quizOrchestrator;
    private SetupOrchestrator setupView;

    public UserInputImpl(QuizOrchestrator quizOrchestrator) {
        this.quizOrchestrator = quizOrchestrator;
        this.setupView = setupView;
    }

    @Override
    public String type() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public boolean selectOption(String message) {
//        Boolean result = null;
//        if (message == "1") {
//
//                result = true;
//        }
//        else
//                result = false;
//                break;
//            case "EXIT":
//                exit();
//                break;
//            default:
//                throw new IllegalOptionException(ExceptionMessages.INVALID_USER_INPUT);
//        }
        return false;
    }

    @Override
    public void enterTitle(String title) {

    }
}
