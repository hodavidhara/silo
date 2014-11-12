package com.hodavidhara.silo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 */
public abstract class Bootstrapper implements ApplicationListener<ContextRefreshedEvent> {

    private boolean hasRun = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRun) {
            bootstrap();
            hasRun = true;
        }
    }

    protected abstract void bootstrap();
}
