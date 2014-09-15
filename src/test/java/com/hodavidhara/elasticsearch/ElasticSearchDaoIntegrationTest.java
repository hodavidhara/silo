package com.hodavidhara.elasticsearch;

import com.hodavidhara.base.IntegrationTestBase;
import com.hodavidhara.model.Indexable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class ElasticSearchDaoIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ElasticSearchDao elasticSearchDao;

    @Test
    public void testCreateDocument() throws IOException {
        Indexable indexable = new Indexable();
        indexable.setName("haha.jpg");
        indexable = elasticSearchDao.createDocument(indexable);
        assertNotNull(indexable);
        assertNotNull(indexable.getId());
        assertEquals("haha.jpg", indexable.getName());
    }

    @Test
    public void testReadDocument() throws IOException {
        Indexable indexable = new Indexable();
        indexable.setName("haha.jpg");
        indexable = elasticSearchDao.createDocument(indexable);
        assertNotNull(indexable);
        assertNotNull(indexable.getId());
        assertEquals("haha.jpg", indexable.getName());

        Indexable retrievedItem = elasticSearchDao.readDocument(indexable.getId(), Indexable.class);
        assertNotNull(retrievedItem);
        assertNotNull(retrievedItem.getId());
        assertEquals("haha.jpg", retrievedItem.getName());
    }

    @Test
    public void testDeleteDocument() throws IOException {
        Indexable indexable = new Indexable();
        indexable.setName("haha.jpg");
        indexable = elasticSearchDao.createDocument(indexable);
        assertNotNull(indexable);
        assertNotNull(indexable.getId());
        assertEquals("haha.jpg", indexable.getName());

        elasticSearchDao.deleteDocument(indexable.getId());
    }
}