package modules;

import com.google.inject.AbstractModule;
import controllers.ClosedQuizContainer;
import controllers.ClosedQuizContainerImpl;

public class QuizContainerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ClosedQuizContainer.class).to(ClosedQuizContainerImpl.class);
    }
}
