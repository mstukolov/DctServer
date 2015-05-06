package com.dct.server.dao.impl;

import com.dct.server.dao.ShopDAO;
import com.dct.server.model.Shop;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 06.05.2015.
 */
@Transactional
@Repository("shopDAO")
public class ShopDAOImpl implements ShopDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Shop search(Shop shop) {
        return null;
    }

    @Override
    public List<Shop> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Shop").list();
    }

    @Override
    public void save(Shop shop) {

    }

    @Override
    public void update(Shop shop) {

    }

    @Override
    public void delete(Shop shop) {

    }
}
