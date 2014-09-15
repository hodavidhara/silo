package com.hodavidhara.document;

import com.hodavidhara.model.Document;

import java.io.InputStream;

/**
 *
 */
public interface DocumentService {

    Document createDocument(Document document, InputStream content);

    Document getDocument(String id);

    void deleteDocument(String id);
}
