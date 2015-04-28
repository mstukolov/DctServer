package com.dct.server.service;

import com.dct.server.model.InventItemBarcode;

import java.util.List;

/**
 * Created by stukolov_m on 28.04.2015.
 */
public interface InventItemBarcodeService {
    InventItemBarcode search(InventItemBarcode inventItemBarcode); // поиск товара по штрих-коду

    List<InventItemBarcode> findAll();

    void save(InventItemBarcode inventItemBarcode);

    void update(InventItemBarcode inventItemBarcode);

    void delete(InventItemBarcode inventItemBarcode);
}
