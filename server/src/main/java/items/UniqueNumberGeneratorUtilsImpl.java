package items;

import java.io.Serializable;import java.lang.Override;

public class UniqueNumberGeneratorUtilsImpl implements UniqueNumberGeneratorUtils, Serializable {
    private static UniqueNumberGeneratorUtils singleton;
    private int id = 0;

    /**
     * Private method to stop other classes from
     * instantiating this object.
     */
    private UniqueNumberGeneratorUtilsImpl() {
    }

    /**
     * Static method that allows people to reach the single
     * instance.
     *
     * @return the single instance.
     */
    public static UniqueNumberGeneratorUtils getInstance() {
        if (singleton == null) {
            singleton = new UniqueNumberGeneratorUtilsImpl();
        }
        return singleton;
    }

    @Override
    public int getUniqueNumber() {
        return id++;
    }
}
