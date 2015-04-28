package com.dct.client;

import com.dct.server.model.InventItemBarcode;
import com.dct.server.service.InventItemBarcodeService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by stukolov_m on 28.04.2015.
 */
public class AppTest {
    public static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) throws IOException {
        System.out.println("write start....");

        InventItemBarcode itemBarcode = new InventItemBarcode();
        itemBarcode.setBarcode("24235346363");
        itemBarcode.setScu("SCU-1234");
        itemBarcode.setSize(39);

        InventItemBarcodeService inventItemBarcodeServices = (InventItemBarcodeService) context.getBean("inventItemBarcodeService");
        inventItemBarcodeServices.save(itemBarcode);


        System.out.println("write end....");
    }
}
