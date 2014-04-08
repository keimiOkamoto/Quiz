package modules;

import com.google.inject.AbstractModule;
import controllers.QuizContainer;
import controllers.QuizContainerImpl;
import factories.ItemsFactory;
import factories.ItemsFactoryImpl;
import factories.PlayerFactory;
import factories.PlayerFactoryImpl;
import models.ScoreKeeper;
import models.ScoreKeeperImpl;

public class QuizSeverModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ItemFactoryModule());
        install(new PlayerFactoryModule());
        install(new QuizContainerModule());
        bind(ItemsFactory.class).to(ItemsFactoryImpl.class);
        bind(PlayerFactory.class).to(PlayerFactoryImpl.class);
        bind(QuizContainer.class).to(QuizContainerImpl.class);
        bind(ScoreKeeper.class).to(ScoreKeeperImpl.class);
    }
}
