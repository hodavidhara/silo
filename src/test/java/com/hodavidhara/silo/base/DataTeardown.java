package com.hodavidhara.silo.base;

import com.hodavidhara.silo.util.TestUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Destroy data created by integration tests after the run.
 */
@Component
@SuppressWarnings("UnusedDeclaration")
public class DataTeardown implements ApplicationListener<ContextClosedEvent> {

    @Value("${tests.data.teardown:true}")
    private boolean teardown;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (teardown) {
            File rootDataDirectory = new File(TestUtil.getDataRootPath());
            try {
                FileUtils.deleteDirectory(rootDataDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
