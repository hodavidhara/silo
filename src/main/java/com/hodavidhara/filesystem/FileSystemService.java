package com.hodavidhara.filesystem;

import java.io.InputStream;

/**
 *
 */
public interface FileSystemService {

    void createDocument(String relativePath, InputStream inputStream);

    void createDirectory(String relativePath);

    void deleteFile(String relativePath);

    String getRootPath();

}
