package utils;

import com.google.inject.Singleton;

import java.io.Serializable;import java.lang.Override;

@Singleton
public class UniqueNumberGeneratorUtilsImpl implements UniqueNumberGeneratorUtils, Serializable {
    private int id = 0;

    @Override
    public int getUniqueNumber() {
        return id++;
    }
}
