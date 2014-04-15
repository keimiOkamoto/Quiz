package modules;

import com.google.inject.AbstractModule;
import utils.DiskWriter;
import utils.DiskWriterImpl;
import utils.UniqueNumberGeneratorUtils;
import utils.UniqueNumberGeneratorUtilsImpl;

public class UniqueNumberGeneratorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DiskWriter.class).to(DiskWriterImpl.class);
    }

}
