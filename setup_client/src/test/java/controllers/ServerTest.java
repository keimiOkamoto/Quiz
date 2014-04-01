package controllers;

import items.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private Registry registry;

    @Before
    public void buildUp() {
        registry = mock(Registry.class);

        server = new ServerImpl(registry);
    }

    @Test
    public void shouldBeAbleToCreateQuizWithAValidTitle () throws RemoteException, NotBoundException {
        String title = "Quiz about cake.";
        Object expectedQuiz = mock(Quiz.class);

        when(registry.lookup(anyString())).thenReturn((java.rmi.Remote) expectedQuiz);
        Quiz actualQuiz = server.createQuiz(title);

        assertEquals(expectedQuiz, actualQuiz);
    }
}
