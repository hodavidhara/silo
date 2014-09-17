package com.hodavidhara.filesystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Paths;

/**
 *
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Value("${data.directory:data}")
    private String dataDirectory;

    @Override
    public void createDocument(String relativePath, InputStream inputStream) {
        FileSystemUtil.createDocument(buildFullPath(relativePath), inputStream);
    }

    @Override
    public void createDirectory(String relativePath) {
        FileSystemUtil.createDirectory(buildFullPath(relativePath));
    }

    @Override
    public void deleteFile(String relativePath) {
        FileSystemUtil.deleteFile(buildFullPath(relativePath));
    }

    @Override
    public String getRootPath() {
        return FileSystemUtil.buildFullPath(dataDirectory);
    }

    private String buildFullPath(String relativePath) {
        return Paths.get(getRootPath(), relativePath).toString();
    }
}
