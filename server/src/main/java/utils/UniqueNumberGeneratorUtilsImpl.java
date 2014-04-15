package utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.Override;

@Singleton
public class UniqueNumberGeneratorUtilsImpl implements UniqueNumberGeneratorUtils {
    private Integer id = 0;
    private final DiskWriter diskWriter;

    @Inject
    public UniqueNumberGeneratorUtilsImpl(DiskWriter diskWriter) {
        if (diskWriter.checkIfIdDataExists()) {
            diskWriter.readDiskForIdFile();
            this.id = diskWriter.getUniqueNumber();
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
        System.out.println(diskWriter);
        System.out.println(id);
        diskWriter.writeIdToDisk(id);
    }

    @Override
    public int getUniqueNumber() {
        return id++;
    }
}
