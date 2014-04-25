package modules;

import com.google.inject.AbstractModule;
import controllers.QuizOrchestrator;
import controllers.QuizOrchestratorImpl;

public class SetupOrchestratorModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new QuizOrchestratorModule());
        bind(QuizOrchestrator.class).to(QuizOrchestratorImpl.class);
    }
}
