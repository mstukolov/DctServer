package com.springapp.mvc;


import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;
import com.dct.server.model.DocumentLines;
import com.dct.server.model.IncomingMessages;
import com.dct.server.service.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

	//21.11.2015 Вторая жизнь
	public static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@RequestMapping(value = "/inventItemBarcodeData/", method = RequestMethod.POST)
	public @ResponseBody String getInventItemBarcodeData(@RequestBody String body, HttpServletRequest request) {

		InventItemBarcodeService inventItemBarcodeServices = (InventItemBarcodeService) context.getBean("inventItemBarcodeService");
		return new Gson().toJson(inventItemBarcodeServices.findAll());
	}

	@RequestMapping(value = "/downloadShops/", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getShops(@RequestBody String body, HttpServletRequest request) throws UnsupportedEncodingException, CharacterCodingException {

		ShopService shopsService = (ShopService) context.getBean("shopService");
		String response = new Gson().toJson(shopsService.findAll());
		String response_ISO_8859_1 = new String(response.getBytes("utf-8"), "ISO-8859-1");

		return response_ISO_8859_1;
	}

	@Transactional
	@RequestMapping(value = "/uploadDocumentsMySQL/", method = RequestMethod.POST/*, produces = "application/json", headers = {"Content-type=application/json"}*/)
	synchronized public @ResponseBody String getData(@RequestBody String body, HttpServletRequest request/*, HttpServletResponse response*/) throws InterruptedException {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String docs = request.getParameter("documents");
		String shop = request.getParameter("shopindex");

		Type type = new TypeToken<List<Document>>(){}.getType();
		List<Document> documents = new Gson().fromJson(docs, type);

		DocumentService documentService = (DocumentService) context.getBean("documentService");
		DocumentLineService documentLineService = (DocumentLineService) context.getBean("documentLineService");

		for(Document item : documents){
			DocumentHeader result =  documentService.search(item);
			//New document creation
			if(result == null) {
				System.out.println("Writing new document: " + item.getDocNum());
				DocumentHeader documentHeader = new DocumentHeader();
				documentHeader.setDocNum(item.getDocNum());
				documentHeader.setDocType(item.getDocType());
				documentHeader.setShopindex(shop);
				try {
					documentHeader.setDocDate(df.parse(item.getDocDate()));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				documentService.create(documentHeader);
				if(item.getLines() != null){
					for(DocumentLines line : item.getLines()){documentLineService.save(line);}
				}

			}
			//Update already existing document
			else
				{
						System.out.println("Already exist doc: " + item.getDocNum());
						List<DocumentLines> documentLines = documentLineService.search(item);
						if(documentLines.size() > 0){
							for(DocumentLines line : documentLines){
								System.out.println("......Line deleted: " + line.getRecid());
								documentLineService.delete(line);
							}
						}
					if(item.getLines().size() > 0)
						{for(DocumentLines line : item.getLines()){documentLineService.save(line);}}

				}
			Thread.sleep(100);
		}
		System.out.println("Writing to DATABASE is FINISHED");
		return "SUCCESS";
	}

	@Transactional
	@RequestMapping(value = "/uploadDocuments/", method = RequestMethod.POST)
	synchronized public @ResponseBody String saveMessage(@RequestBody String body, HttpServletRequest request) throws InterruptedException {

		String docs = request.getParameter("documents");
		String shop = request.getParameter("shopindex");
		String numrecs = request.getParameter("numrecs");
		IncomingMessagesService messagesServiceService = (IncomingMessagesService) context.getBean("incomingMessagesService");

		if(body != null){
			IncomingMessages messages = new IncomingMessages();
			messages.setShopindex(shop);
			messages.setReceivedDateTime(new Date());
			messages.setStatus(0);
			messages.setNumRecs(Integer.valueOf(numrecs));
			messages.setTryCount(0);
			messages.setMessageString(docs);
			messages.setErrorNo(0);
			messagesServiceService.save(messages);
		}
		return "SUCCESS";
	}
	@Transactional
	@RequestMapping(value = "/processingQueue", method = RequestMethod.GET)
	public String processingQueue(ModelMap model) throws InterruptedException {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date startDate, endDate;
		startDate = new Date();


		//Получение списка необработанных сообщений
		IncomingMessagesService messagesServiceService = (IncomingMessagesService) context.getBean("incomingMessagesService");

		if(!messagesServiceService.findOpenedMessages().isEmpty()){

			for(IncomingMessages message : messagesServiceService.findOpenedMessages()){
				writeDocumentsToDatabase(message);
				message.setTryCount(message.getTryCount() + 1);
				message.setStatus(1);
				message.setFinishedDateTime(new Date());
				messagesServiceService.update(message);
			}
		}

		endDate = new Date();
		long totalTime = (endDate.getTime() - startDate.getTime())/(1000);
		model.addAttribute("startDate", df.format(startDate));
		model.addAttribute("endDate", df.format(endDate));
		model.addAttribute("totalTime", totalTime);

		return "test";
	}
	public void writeDocumentsToDatabase(IncomingMessages message){

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String docs = message.getMessageString();
		String shop = message.getShopindex();

		Type type = new TypeToken<List<Document>>(){}.getType();
		List<Document> documents = new Gson().fromJson(docs, type);

		DocumentService documentService = (DocumentService) context.getBean("documentService");
		DocumentLineService documentLineService = (DocumentLineService) context.getBean("documentLineService");

		for(Document item : documents){
			DocumentHeader result =  documentService.search(item);
			//New document creation
			if(result == null) {
				System.out.println("Writing new document: " + item.getDocNum());
				DocumentHeader documentHeader = new DocumentHeader();
				documentHeader.setDocNum(item.getDocNum());
				documentHeader.setDocType(item.getDocType());
				documentHeader.setShopindex(shop);
				documentHeader.setStatus(0);
				try {
					documentHeader.setDocDate(df.parse(item.getDocDate()));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				documentService.create(documentHeader);
				if(item.getLines() != null){
					for(DocumentLines line : item.getLines())
					{
						line.setDocType(item.getDocType());
						documentLineService.save(line);
					}
				}

			}
			//Update already existing document
			else
			{
				//STUM Установка статуса импорта в 1С в нулевое значение
				result.setStatus(0);
				documentService.update(result);

				System.out.println("Already exist doc: " + item.getDocNum());
				List<DocumentLines> documentLines = documentLineService.search(item);
				if(documentLines.size() > 0){
					for(DocumentLines line : documentLines){
						System.out.println("......Line deleted: " + line.getRecid());
						documentLineService.delete(line);
					}
				}
				if(item.getLines().size() > 0)
				{
					for(DocumentLines line : item.getLines())
					{
						line.setDocType(item.getDocType());
						documentLineService.save(line);
					}
				}
			}
		}
		System.out.println("Writing to DATABASE is FINISHED");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		model.addAttribute("msg", "Server is on connection!!!");
		return "test";
	}

}