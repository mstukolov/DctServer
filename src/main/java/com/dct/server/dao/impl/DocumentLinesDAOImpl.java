package com.dct.server.dao.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.dao.DocumentLinesDAO;
import com.dct.server.model.DocumentHeader;
import com.dct.server.model.DocumentLines;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Transactional
@Repository("documnentDAO")
public class DocumentLinesDAOImpl implements DocumentLinesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DocumentLines search(DocumentLines line) {
        return null;
    }

    @Override
    public List<DocumentLines> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from DocumentLines").list();
    }

    @Override
    public void save(DocumentLines line) {
        sessionFactory.getCurrentSession().persist(line);
    }

    @Override
    public void update(DocumentLines line) {
        sessionFactory.getCurrentSession().update(line);
    }

    @Override
    public void delete(DocumentLines line) {
        sessionFactory.getCurrentSession().delete(line);
    }
}
