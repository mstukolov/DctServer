package com.dct.client;

import com.dct.server.model.DocumentHeader;
import com.dct.server.model.IncomingMessages;
import com.dct.server.service.DocumentService;
import com.dct.server.service.IncomingMessagesService;
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

        IncomingMessages messages = new IncomingMessages();
        messages.setPackageNo(101);
        messages.setShopindex("45");
        messages.setReceivedDateTime(new Date());
        messages.setFinishedDateTime(new Date());
        messages.setStatus(1);
        messages.setNumRecs(100);
        messages.setTryCount(1);
        messages.setMessageString("Hello Maxim!!!");
        messages.setErrorNo(0);

        IncomingMessagesService service = (IncomingMessagesService) context.getBean("incomingMessagesService");
        service.save(messages);


        System.out.println("write end....");
    }
}
