package utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.Serializable;import java.lang.Override;

@Singleton
public class UniqueNumberGeneratorUtilsImpl implements UniqueNumberGeneratorUtils, Serializable {
    private int id;
    private DiskWriter diskWriter;

    @Inject
    public UniqueNumberGeneratorUtilsImpl(DiskWriter diskWriter) {
        if (diskWriter.checkIfIdDataExists()) {
            diskWriter.readDisk();
            this.id = diskWriter.getUniqueNumberGenerator();
        } else {
            this.id = 0;
        }
        this.diskWriter = diskWriter;
        addShutdownHook();
    }

    /**
     * This method adds a shutdown hook
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                flush();
            }
        });
    }

    @Override
    public void flush() {
        diskWriter.writeIdToDisk(id);
    }

    @Override
    public int getUniqueNumber() {
        return id++;
    }


}
