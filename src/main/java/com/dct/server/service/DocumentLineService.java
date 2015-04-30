package com.dct.server.service;

import com.dct.server.model.DocumentHeader;
import com.dct.server.model.DocumentLines;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
public interface DocumentLineService {
    DocumentLines search(DocumentLines line); //

    List<DocumentLines> findAll();

    void save(DocumentLines line);

    void update(DocumentLines line);

    void delete(DocumentLines line);
}
