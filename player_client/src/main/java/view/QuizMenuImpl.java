package view;

import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class QuizMenuImpl implements QuizMenu {

    private List<Quiz> quizList;

    public QuizMenuImpl(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public void printListOfQuizzes() throws RemoteException {
        for (Quiz quiz : quizList) {
            System.out.println(quiz.getTitle());
        }
    }

    @Override
    public void getUserQuizChoice(Scanner scanner) {
        System.out.println("Please enter the quiz number you want to play!");

        String input = scanner.nextLine();

    }
}
