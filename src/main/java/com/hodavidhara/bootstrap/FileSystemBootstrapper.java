package com.hodavidhara.bootstrap;

import com.hodavidhara.filesystem.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 *
 */
@Component
public class FileSystemBootstrapper extends Bootstrapper {

    @Autowired
    private FileSystemService fileSystemService;

    @Override
    protected void bootstrap() {
        File baseDataFile = new File(fileSystemService.getRootPath());
        if (!baseDataFile.exists()) {
            boolean success = baseDataFile.mkdir();
            if (!success) {
                throw new RuntimeException("Error creating root data folder.");
            }
        }
    }
}
