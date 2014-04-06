package controllers;

import models.QuizServer;

/**
 * This class links the setup client to the server.
 */
public interface ServerLink {

    QuizServer getQuizServer();
}
