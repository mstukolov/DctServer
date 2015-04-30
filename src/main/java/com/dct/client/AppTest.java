package com.dct.client;

import com.dct.server.model.DocumentHeader;
import com.dct.server.service.DocumentService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Date;

/**
 * Created by stukolov_m on 28.04.2015.
 */
public class AppTest {
    public static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) throws IOException {
        System.out.println("write start....");

        DocumentHeader document = new DocumentHeader();
        document.setDocNum("24235346363");
        document.setDocType("arrival");
        document.setDocDate(new Date());
        document.setShopindex("54");

        DocumentService documentService = (DocumentService) context.getBean("documentService");
        documentService.save(document);


        System.out.println("write end....");
    }
}
