package controllers;

import models.Quiz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerLinkImpl implements ServerLink {

    private Registry registry;

    public ServerLinkImpl() {
        try {
            this.registry = LocateRegistry.getRegistry("localhost", 1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public QuizServer getQuizServer() {
        QuizServer quizServer = null;
        try {
             quizServer = (QuizServer) registry.lookup("QuizServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return quizServer;
    }

    /**
     * Interface for the quiz server.
     */
    public static interface QuizServer {

        /**
         * Checks if the title already exists.
         *
         * @param title A String title.
         * @return false if the same title already exists.
         */
        boolean titleIsValid(String title);

        /**
         * Checks if the ID exists.
         *
         * @param id An ID of a Quiz.
         * @return false if the ID doesn't exist.
         */
        boolean iDIsValid(int id);

        /**
         * Ends quiz by given ID.
         *
         * @param id The ID ofa quiz.
         */
        void endQuiz(int id);

        /**
         * Saves a quiz to the server.
         *
         * @param quiz A quiz.
         */
        void save(Quiz quiz);

        /**
         * Gets a ItemsFactory object.
         *
         * @return An ItemsFactory.
         */
        ItemsFactory getItemsFactory();
    }
}
