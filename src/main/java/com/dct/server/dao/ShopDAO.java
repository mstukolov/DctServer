package com.dct.server.dao;


import com.dct.server.model.Shop;

import java.util.List;

/**
 * Created by stukolov_m on 06.05.2015.
 */
public interface ShopDAO {

    Shop search(Shop shop);

    List<Shop> findAll();

    void save(Shop shop);

    void update(Shop shop);

    void delete(Shop shop);
}
