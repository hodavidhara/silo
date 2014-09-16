package com.hodavidhara.content.directory;

import com.hodavidhara.base.IntegrationTestBase;
import com.hodavidhara.util.TestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;

/**
 *
 */
public class DirectoryServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    private DirectoryService directoryService;

    @Test
    public void testCreateDirectory() {
        Directory directory = directoryService.createDirectory("/test/");
        assertNotNull(directory);
        assertNotNull(directory.getId());
        assertEquals("test", directory.getName());
        assertEquals("/test/", directory.getPath());

        assertDirectoryExistsOnFileSystem("/test/");

        directory = directoryService.getDirectoryById(directory.getId());
        assertNotNull(directory);
        assertNotNull(directory.getId());
        assertEquals("test", directory.getName());
        assertEquals("/test/", directory.getPath());
    }

    @Test
    public void testCreateDirectoryMultipleLevels() {
        Directory directory = directoryService.createDirectory("/a/b/c/");
        assertNotNull(directory);
        assertNotNull(directory.getId());
        assertEquals("c", directory.getName());
        assertEquals("/a/b/c/", directory.getPath());

        assertDirectoryIsFullyPersisted("a", "/a/");
        assertDirectoryIsFullyPersisted("b", "/a/b/");
        assertDirectoryIsFullyPersisted("c", "/a/b/c/");
    }

    private void assertDirectoryIsFullyPersisted(String expectedName, String expectedPath) {
        assertDirectoryExistsOnFileSystem(expectedPath);

        Directory directory = directoryService.getDirectoryByPath(expectedPath);
        assertNotNull(directory);
        assertNotNull(directory.getId());
        assertEquals(expectedName, directory.getName());
        assertEquals(expectedPath, directory.getPath());
    }

    private void assertDirectoryExistsOnFileSystem(String expectedPath) {
        File file = new File(TestUtil.buildFullPath(expectedPath));
        assertTrue(file.exists());
    }
}
