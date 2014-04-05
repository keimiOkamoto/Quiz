package modules;

import com.google.inject.AbstractModule;
import utils.UniqueNumberGeneratorUtils;
import utils.UniqueNumberGeneratorUtilsImpl;

public class ItemFactoryModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(UniqueNumberGeneratorUtils.class).to(UniqueNumberGeneratorUtilsImpl.class);
    }
}
