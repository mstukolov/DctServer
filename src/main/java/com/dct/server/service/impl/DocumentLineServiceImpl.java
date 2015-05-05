package com.dct.server.service.impl;

import com.dct.server.dao.DocumentLinesDAO;
import com.dct.server.model.Document;
import com.dct.server.model.DocumentLines;
import com.dct.server.service.DocumentLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Service("documentLineService")
public class DocumentLineServiceImpl implements DocumentLineService {

    @Autowired
    DocumentLinesDAO documentLinesDAO;

    @Override
    public DocumentLines search(DocumentLines line) {
        return null;
    }

    @Override
    public List<DocumentLines> search(Document document) {
        return documentLinesDAO.search(document);
    }

    @Override
    public List<DocumentLines> findAll() {
        return null;
    }

    @Override
    public void save(DocumentLines line) {
            documentLinesDAO.save(line);
    }

    @Override
    public void update(DocumentLines line) {
        documentLinesDAO.update(line);
    }

    @Override
    public void delete(DocumentLines line) {
        documentLinesDAO.delete(line);
    }
}
