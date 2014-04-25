package modules;

import com.google.inject.AbstractModule;
import controllers.Server;
import controllers.ServerImpl;

public class QuizPlayerOrchestratorModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ServerModule());
        bind(Server.class).to(ServerImpl.class);
    }
}
