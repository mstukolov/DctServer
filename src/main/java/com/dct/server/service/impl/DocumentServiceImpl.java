package com.dct.server.service.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;
import com.dct.server.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentDAO documentDAO;

    @Override
    public DocumentHeader search(Document document) {
        return documentDAO.search(document);
    }

    @Override
    public List<DocumentHeader> findAll() {
        return documentDAO.findAll();
    }

    @Override
    public void create(DocumentHeader document) {
        documentDAO.create(document);
    }

    @Override
    public void update(DocumentHeader document) {
        documentDAO.update(document);
    }

    @Override
    public void delete(DocumentHeader document) {
        documentDAO.delete(document);
    }

    @Override
    public void deleteDocumentLines(Document document) {
        documentDAO.deleteDocumentLines(document);
    }
}
