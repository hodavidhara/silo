package com.hodavidhara.silo.content.directory;

/**
 * The DirectoryService is responsible for operations on Directories.
 */
public interface DirectoryService {

    /**
     * Create a new Directory at the given path. If the parent directories do not exist for the given path,
     * it will create them as well.
     *
     * @param path The path to create the directory at.
     * @return The newly created Directory.
     */
    Directory createDirectory(String path);

    Directory getDirectoryByPath(String path);

    Directory getDirectoryById(String id);
}
