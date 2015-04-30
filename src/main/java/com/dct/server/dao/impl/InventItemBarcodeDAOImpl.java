package com.dct.server.dao.impl;

import com.dct.server.dao.InventItemBarcodeDAO;
import com.dct.server.model.InventItemBarcode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 28.04.2015.
 */
@Transactional
@Repository("InventItemBarcodeDAO")
public class InventItemBarcodeDAOImpl implements InventItemBarcodeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InventItemBarcode search(InventItemBarcode inventItemBarcode) {
        return null;
    }

    @Override
    public List<InventItemBarcode> findAll() {

        return sessionFactory.getCurrentSession().createQuery("from InventItemBarcode").list();
    }

    @Override
    public void save(InventItemBarcode inventItemBarcode) {
        sessionFactory.getCurrentSession().persist(inventItemBarcode);
    }

    @Override
    public void update(InventItemBarcode inventItemBarcode) {
        sessionFactory.getCurrentSession().update(inventItemBarcode);
    }

    @Override
    public void delete(InventItemBarcode inventItemBarcode) {
        sessionFactory.getCurrentSession().delete(inventItemBarcode);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
