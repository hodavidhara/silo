package com.hodavidhara.silo.content.document;

import com.hodavidhara.silo.elasticsearch.ElasticSearchDao;
import com.hodavidhara.silo.elasticsearch.Type;
import com.hodavidhara.silo.filesystem.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 *
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private ElasticSearchDao elasticSearchDao;
    @Autowired
    private FileSystemService fileSystemService;

    @Override
    public Document createDocument(Document document, InputStream content) {
        // TODO: Change this when introducing folder support.
        document.setPath("/" + document.getName());

        // Try to create the file on the local disk.
        fileSystemService.createDocument(document.getPath(), content);

        // Create the representation in ES
        document = elasticSearchDao.create(document, Type.DOCUMENT);
        return document;
    }

    @Override
    public Document getDocument(String id) {
        return elasticSearchDao.read(id, Type.DOCUMENT);
    }

    @Override
    public void deleteDocument(String id) {
        Document document = elasticSearchDao.read(id, Type.DOCUMENT);

        if (document != null) {
            fileSystemService.deleteFile(document.getPath());
            elasticSearchDao.delete(id, Type.DOCUMENT);
        }
    }
}
