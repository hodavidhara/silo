package com.hodavidhara.silo.content.document;

import com.hodavidhara.silo.base.IntegrationTestBase;
import com.hodavidhara.silo.util.TestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 *
 */
public class DocumentServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    private DocumentService documentService;

    @Test
    public void testCreateDocument() {
        Document document = new Document();
        document.setName(UUID.randomUUID().toString() + ".jpg");
        document = documentService.createDocument(document, TestUtil.getRandomImageStream());
        assertNotNull(document);
        assertNotNull(document.getName());
        File file = new File(TestUtil.buildFullPath(document.getPath()));
        assertTrue(file.exists());
    }

    @Test
    public void testCreateDocumentWithCustomProperty() {
        Document document = new Document();
        document.setName(UUID.randomUUID().toString() + ".jpg");
        document.setProperty("custom", "awesomeness");
        document = documentService.createDocument(document, TestUtil.getRandomImageStream());
        assertNotNull(document);
        assertNotNull(document.getName());
        assertEquals("awesomeness", document.getProperty("custom"));
        File file = new File(TestUtil.buildFullPath(document.getPath()));
        assertTrue(file.exists());
    }

    @Test
    public void testDeleteDocument() {
        Document document = new Document();
        document.setName(UUID.randomUUID().toString() + ".jpg");
        document = documentService.createDocument(document, TestUtil.getRandomImageStream());
        assertNotNull(document);
        assertNotNull(document.getName());
        File file = new File(TestUtil.buildFullPath(document.getPath()));
        assertTrue(file.exists());

        documentService.deleteDocument(document.getId());
        file = new File(TestUtil.buildFullPath(document.getPath()));
        assertFalse(file.exists());
    }
}
