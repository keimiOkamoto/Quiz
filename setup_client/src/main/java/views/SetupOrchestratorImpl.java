package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;
import controllers.*;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;

public class SetupOrchestratorImpl implements SetupOrchestrator {
    private QuizOrchestrator quizOrchestrator;
    private String userInput;
    private String message = null;
    private String answer;

    public SetupOrchestratorImpl(QuizOrchestrator quizOrchestrator) {
        this.quizOrchestrator = quizOrchestrator;
    }

    @Override
    public String getMessageForQuizTitle() throws RemoteException {
        if (userInput == null) {
            message = SetUpMessages.START_MESSAGE;

        } else if (userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT + "\n");
            message = SetUpMessages.START_MESSAGE;;

        } else if (userInput.equals(SetUpMessages.ONE)) {
            message = SetUpMessages.ENTER_QUIZ_TITLE;

        } else if (userInput.equals(SetUpMessages.TWO)) {
            message = SetUpMessages.ENTER_QUIZ_ID_REQUEST;

        } else if (message.equals(SetUpMessages.ENTER_QUIZ_TITLE)) {
            try {
                createQuizTitle(userInput);
                message = SetUpMessages.REQUEST_QUESTION;
            } catch (IllegalQuizException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (RemoteException e) {
                System.out.println("poop");
                e.printStackTrace();
            }
        } else {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT + "\n");
        }
        return message;
    }

    @Override
    public String getMessageForQuestion(String userInput) throws RemoteException {
        try {
            addQuestion(userInput);
            message = SetUpMessages.REQUEST_ANSWER;
        } catch (IllegalArgumentException | IllegalQuestionException | IllegalQuizException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    @Override
    public String getMessageForAnswer(String userInput) throws RemoteException, IllegalArgumentException {
        if (userInput == null || userInput.trim().isEmpty()) {
            message = SetUpMessages.REQUEST_ANSWER;
            System.out.println(ExceptionMessages.EMPTY_ANSWER);

        } else if (userInput.equals(SetUpMessages.DONE)) {
            message = SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS;

        } else {
            setAnswer(userInput);
            message = SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST;
        }
        return message;
    }

    @Override
    public String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException {
        if (yesOrNo.equals(SetUpMessages.YES) || yesOrNo.equals(SetUpMessages.NO)) {
            boolean value = correct(yesOrNo);
            addAnswer(value);
            message = SetUpMessages.REQUEST_ANSWER;

        } else {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST;
        }
        return message;
    }

    @Override
    public String getMessageForSave(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS;

        } else if (userInput.equals(SetUpMessages.SAVE)) {
            save();
            message = SetUpMessages.SAVE_SUCCESS;

        } else if (userInput.equals((SetUpMessages.YES))) {
            message = SetUpMessages.REQUEST_QUESTION;
        }
        return message;
    }

    @Override
    public String getMessageForCloseQuiz(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.ENTER_QUIZ_ID_REQUEST;

        } else {
            try {
                if (close(userInput)) {
                    message = SetUpMessages.QUIZ_CLOSED_SUCCESS;
                } else {
                    message = SetUpMessages.ENTER_QUIZ_ID_REQUEST;
                }
            } catch (RemoteException e) {
                System.out.println(ExceptionMessages.SERVER_ERROR);
            } catch (IllegalQuizException e) {
                System.out.println(e.getMessage());
                message = SetUpMessages.START_MESSAGE;
            }
        }
        return message;
    }

    @Override
    public void setInput(String userAnswer) {
        this.userInput = userAnswer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    /*
     * Helper method to check if the used input is valid.
     */
    private boolean correct(String userInput) {
        boolean value = false;
        if (userInput.trim().equals(SetUpMessages.YES)) {
            value = true;
        } else if (userInput.trim().equals(SetUpMessages.NO)) {
            value = false;
        }
        return value;
    }

    /*
    * Quiz orchestrator,
    * Helper for public methods.
    */
    private void createQuizTitle(String userAnswer) throws
            RemoteException, IllegalQuizException, IllegalArgumentException {
        int id = quizOrchestrator.createQuiz(userAnswer);
        System.out.println(SetUpMessages.YOUR_QUIZ_ID + id);
    }

    private void addQuestion(String userInput) throws
            RemoteException, IllegalQuizException, IllegalArgumentException, IllegalQuestionException {
        quizOrchestrator.addQuestion(userInput);
    }

    private boolean close(String userInput) throws RemoteException, IllegalQuizException {
        int id;
        boolean result = false;
        try {
            id = Integer.parseInt(userInput);
            quizOrchestrator.closeQuiz(id);
            result = true;
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.NO_NUMBER_ENTERED);
        }
        return result;
    }

    private void addAnswer(boolean yesOrNo) throws RemoteException, IllegalQuestionException, IllegalArgumentException {
        quizOrchestrator.addAnswer(getAnswer(), yesOrNo);
    }

    private void save() {
        try {
            quizOrchestrator.save();
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
    }
}
