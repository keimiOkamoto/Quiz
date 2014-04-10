package factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Player;
import models.PlayerImpl;
import utils.UniqueNumberGeneratorUtils;

import java.rmi.RemoteException;

@Singleton
public class PlayerFactoryImpl implements PlayerFactory {
    private UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils;

    @Inject
    public PlayerFactoryImpl(UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils) {
        this.uniqueNumberGeneratorUtils = uniqueNumberGeneratorUtils;
    }

    @Override
    public Player generatePlayer(String name, String country, int age) throws RemoteException {
        return new PlayerImpl(name, country, age, uniqueNumberGeneratorUtils.getUniqueNumber());
    }
}
