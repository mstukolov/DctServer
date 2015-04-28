package com.dct.server.service.impl;

import com.dct.server.dao.InventItemBarcodeDAO;
import com.dct.server.model.InventItemBarcode;
import com.dct.server.service.InventItemBarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 28.04.2015.
 */
@Service("inventItemBarcodeService")
public class InventItemBarcodeServiceImpl implements InventItemBarcodeService{

    @Autowired
    InventItemBarcodeDAO inventItemBarcodeDAO;

    @Override
    public InventItemBarcode search(InventItemBarcode inventItemBarcode) {
        return null;
    }

    @Override
    public List<InventItemBarcode> findAll() {
        return null;
    }

    @Override
    public void save(InventItemBarcode inventItemBarcode) {
        inventItemBarcodeDAO.save(inventItemBarcode);
    }

    @Override
    public void update(InventItemBarcode inventItemBarcode) {

    }

    @Override
    public void delete(InventItemBarcode inventItemBarcode) {

    }
}
