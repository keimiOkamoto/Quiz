package modules;

import com.google.inject.AbstractModule;
import controllers.ServerLink;
import controllers.ServerLinkImpl;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServerLink.class).to(ServerLinkImpl.class);
    }
}
