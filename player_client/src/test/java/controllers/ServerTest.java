package controllers;

import models.Player;
import models.PlayerFactory;
import models.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private QuizServer quizServer;
    private ServerLink serverLink;
    private PlayerFactory playerFactory;
    private Quiz quiz;

    @Before
    public void buildUp() {
        quiz = mock(Quiz.class);
        serverLink = mock(ServerLink.class);
        quizServer = mock(QuizServer.class);
        playerFactory = mock(PlayerFactory.class);
        when(serverLink.getQuizServer()).thenReturn(quizServer);
        when(quizServer.getPlayerFactory()).thenReturn(playerFactory);
        server = new ServerImpl(serverLink);
    }

    @Test
    public void shouldBeAbleToGetListOfQuizzes() {
        server.getQuizzes();
        verify(quizServer).getQuizzes();
    }

    @Test
    public void shouldBeAbleToGetQuizById() {
        int id = 5;
        server.getQuiz(id);
        verify(quizServer).getQuiz(anyInt());
    }

    @Test
    public void shouldBeAbleToCreateNewPlayer() throws RemoteException {
        int age = 5;
        String name = "Superman";
        String country = "Krypton";

        server.createPlayer(name, country, age);
        verify(playerFactory).generatePlayer(anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldBeAbleToGetWinnerByQuizId() {
        int quizId = 5;
        server.getWinnerBy(quizId);
        verify(quizServer).getWinnerBy(quizId);
    }

    @Test
    public void shouldBeAbleSetPlayerAsWinner() {
        Player player = mock(Player.class);
        int score = 5;
        server.setPlayerAsWinner(player, quiz, score);
        verify(quizServer).setPlayerAsWinner(player, quiz);
    }
}
