package com.hodavidhara.content.directory;

/**
 *
 */
public interface DirectoryService {

    Directory createDirectory(String path);

    Directory getDirectoryByPath(String path);

    Directory getDirectoryById(String id);
}
