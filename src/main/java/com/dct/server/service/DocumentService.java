package com.dct.server.service;

import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
public interface DocumentService {
    DocumentHeader search(Document document); //

    List<DocumentHeader> findAll();

    void create(DocumentHeader document);

    void update(DocumentHeader document);

    void delete(DocumentHeader document);

    void deleteDocumentLines(Document document);
}
