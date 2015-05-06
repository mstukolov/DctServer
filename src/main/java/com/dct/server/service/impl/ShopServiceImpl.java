package com.dct.server.service.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.dao.ShopDAO;
import com.dct.server.model.Shop;
import com.dct.server.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 06.05.2015.
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAO shopDAO;

    @Override
    public Shop search(Shop shop) {
        return null;
    }

    @Override
    public List<Shop> findAll() {
        return shopDAO.findAll();
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
