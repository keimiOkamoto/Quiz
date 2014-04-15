package modules;

import com.google.inject.AbstractModule;
import utils.DiskWriter;
import utils.DiskWriterImpl;

public class UniqueNumberGeneratorUtilsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DiskWriter.class).to(DiskWriterImpl.class);
    }

}
