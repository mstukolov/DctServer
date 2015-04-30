package com.dct.server.dao.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.model.DocumentHeader;
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
public class DocumentDAOImpl implements DocumentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DocumentHeader search(DocumentHeader documnent) {
        return null;
    }

    @Override
    public List<DocumentHeader> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Documnent").list();
    }

    @Override
    public void save(DocumentHeader documnent) {
        sessionFactory.getCurrentSession().persist(documnent);
    }

    @Override
    public void update(DocumentHeader documnent) {
        sessionFactory.getCurrentSession().update(documnent);
    }

    @Override
    public void delete(DocumentHeader documnent) {
        sessionFactory.getCurrentSession().delete(documnent);
    }
}
