package factories;

import models.Player;
import models.PlayerImpl;
import utils.UniqueNumberGeneratorUtils;

public class PlayerFactoryImpl implements PlayerFactory {
    private UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils;

    public PlayerFactoryImpl(UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils) {
        this.uniqueNumberGeneratorUtils = uniqueNumberGeneratorUtils;
    }

    @Override
    public Player generatePlayer(String name, String country, int age) {
        return new PlayerImpl(name, country, age, uniqueNumberGeneratorUtils.getUniqueNumber());
    }
}
