package com.hodavidhara.silo.bootstrap;

import com.hodavidhara.silo.filesystem.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Bootstrapper to create the root folder where all Silo data is stored, including ElasticSearch index data,
 * and binary assets.
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
