package factories;

import models.Player;
import org.junit.Test;
import utils.UniqueNumberGeneratorUtils;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerFactoryTest {

    @Test
    public void shouldBeAbleToGeneratePlayer() throws RemoteException {
        UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils = mock(UniqueNumberGeneratorUtils.class);
        PlayerFactory playerFactory = new PlayerFactoryImpl(uniqueNumberGeneratorUtils);

        String name = "Batman";
        String country = "America";
        int age = 5;
        int id = 12;

        when(uniqueNumberGeneratorUtils.getUniqueNumber()).thenReturn(id);
        Player actual = playerFactory.generatePlayer(name, country, age);

        int actualId = actual.getId();

        assertEquals(id, actualId);
    }
}
