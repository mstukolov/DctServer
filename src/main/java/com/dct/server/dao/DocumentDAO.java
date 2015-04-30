package com.dct.server.dao;

import com.dct.server.model.DocumentHeader;

import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
public interface DocumentDAO {

    DocumentHeader search(DocumentHeader documnent); //

    List<DocumentHeader> findAll();

    void save(DocumentHeader documnent);

    void update(DocumentHeader documnent);

    void delete(DocumentHeader documnent);
}
