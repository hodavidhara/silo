package com.hodavidhara.content.document;

import java.io.InputStream;

/**
 *
 */
public interface DocumentService {

    Document createDocument(Document document, InputStream content);

    Document getDocument(String id);

    void deleteDocument(String id);
}
