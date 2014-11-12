package com.hodavidhara.silo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Abstract class for running a routine once when the Spring's ContextRefreshedEvent is fired.
 */
public abstract class Bootstrapper implements ApplicationListener<ContextRefreshedEvent> {

    private boolean hasRun = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRun) {
            try {
                bootstrap();
            } finally {
                hasRun = true;
            }
        }
    }

    /**
     * The routine to be run when the Spring application begins. Will only be run once.
     */
    protected abstract void bootstrap();
}
