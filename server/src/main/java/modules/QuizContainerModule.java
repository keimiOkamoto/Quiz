package modules;

import com.google.inject.AbstractModule;
import controllers.ClosedQuizContainer;
import controllers.ClosedQuizContainerImpl;
import utils.DiskWriter;
import utils.DiskWriterImpl;

public class QuizContainerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ClosedQuizContainer.class).to(ClosedQuizContainerImpl.class);
        bind(DiskWriter.class).to(DiskWriterImpl.class);
    }
}
