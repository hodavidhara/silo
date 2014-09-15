package com.hodavidhara.file;

import java.io.InputStream;

/**
 *
 */
public interface FileSystemService {

    void createFile(String relativePath, InputStream inputStream);

    void deleteFile(String relativePath);

    String getRootPath();

}
