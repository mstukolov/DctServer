package com.dct.server.service.impl;

import com.dct.server.model.DocumentLines;
import com.dct.server.service.DocumentLineService;
import com.dct.server.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Service("documentLineService")
public class DocumentLineServiceImpl implements DocumentLineService {
    @Override
    public DocumentLines search(DocumentLines line) {
        return null;
    }

    @Override
    public List<DocumentLines> findAll() {
        return null;
    }

    @Override
    public void save(DocumentLines line) {

    }

    @Override
    public void update(DocumentLines line) {

    }

    @Override
    public void delete(DocumentLines line) {

    }
}
