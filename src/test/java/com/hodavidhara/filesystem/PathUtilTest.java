package com.hodavidhara.filesystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 */
public class PathUtilTest {

    @Test
    public void testGetParent() {
        assertNull(PathUtil.getParent(null));
        assertNull(PathUtil.getParent(""));
        assertNull(PathUtil.getParent("/"));

        assertEquals("/", PathUtil.getParent("/a"));
        assertEquals("/", PathUtil.getParent("/a/"));

        assertEquals("/a/", PathUtil.getParent("/a/b"));
        assertEquals("/a/", PathUtil.getParent("/a/b/"));
    }

    @Test
    public void testGetFileName() {
        assertNull(PathUtil.getFilename(null));
        assertNull(PathUtil.getFilename(""));
        assertNull(PathUtil.getFilename("/"));

        assertEquals("a", PathUtil.getFilename("/a"));
        assertEquals("a", PathUtil.getFilename("/a/"));

        assertEquals("b", PathUtil.getFilename("/a/b"));
        assertEquals("b", PathUtil.getFilename("/a/b/"));

        assertEquals("c.txt", PathUtil.getFilename("/a/b/c.txt"));
    }
}
