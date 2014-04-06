package controllers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private QuizServer quizServer;
    private ServerLink serverLink;

    @Before
    public void buildUp() {
        serverLink = mock(ServerLink.class);
        quizServer = mock(QuizServer.class);
        when(serverLink.getQuizServer()).thenReturn(quizServer);
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
    public void shouldBeAbleToCheckForHighScore() {
        Player player = mock(Player.class);
        server.checkForHighScore(player);
        verify(quizServer).checkForHighScore(player);
    }

}
