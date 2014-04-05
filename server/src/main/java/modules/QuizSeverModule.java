package modules;

import com.google.inject.AbstractModule;
import controllers.QuizContainer;
import controllers.QuizContainerImpl;
import factories.ItemsFactory;
import factories.ItemsFactoryImpl;

public class QuizSeverModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ItemFactoryModule());
        install(new QuizContainerModule());
        bind(ItemsFactory.class).to(ItemsFactoryImpl.class);
        bind(QuizContainer.class).to(QuizContainerImpl.class);
    }
}
