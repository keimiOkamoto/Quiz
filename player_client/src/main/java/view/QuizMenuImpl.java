package view;

import constants.ExceptionMessages;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

public class QuizMenuImpl implements QuizMenu {

    private List<Quiz> quizList;

    public QuizMenuImpl(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public void printListOfQuizzes() throws RemoteException {
        for (int x = 1; x <= quizList.size(); x++) {
            Quiz quiz = quizList.get(x - 1);
            System.out.print(x + ". ");
            try {
                System.out.println(quiz.getTitle());
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new RemoteException(ExceptionMessages.SERVER_ERROR);
            }
        }
    }

    @Override
    public String getQuizNumberMessage() {
        return "\t\t\t♬ ☆ ☆ ☆ Welcome to the Quiz Game! ☆ ☆ ☆ ♬\n❤ Please enter the quiz number you want to play! ❤ ";
    }
}
