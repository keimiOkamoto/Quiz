package modules;

import com.google.inject.AbstractModule;
import factories.ItemsFactory;
import factories.ItemsFactoryImpl;

public class ScoreKeeperModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ItemsFactory.class).to(ItemsFactoryImpl.class);
    }
}
