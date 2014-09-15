package com.hodavidhara.document;

import com.hodavidhara.elasticsearch.ElasticSearchDao;
import com.hodavidhara.file.FileSystemService;
import com.hodavidhara.model.Document;
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
        fileSystemService.createFile(document.getPath(), content);

        // Create the representation in ES
        document = elasticSearchDao.createDocument(document);
        return document;
    }

    @Override
    public Document getDocument(String id) {
        return elasticSearchDao.readDocument(id, Document.class);
    }

    @Override
    public void deleteDocument(String id) {
        Document document = elasticSearchDao.readDocument(id, Document.class);

        if (document != null) {
            fileSystemService.deleteFile(document.getPath());
            elasticSearchDao.deleteDocument(id);
        }
    }
}
