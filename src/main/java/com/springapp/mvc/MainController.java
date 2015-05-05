package com.springapp.mvc;


import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;
import com.dct.server.model.DocumentLines;
import com.dct.server.service.DocumentLineService;
import com.dct.server.service.DocumentService;
import com.dct.server.service.InventItemBarcodeService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

	public static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@RequestMapping(value = "/inventItemBarcodeData/", method = RequestMethod.POST)
	public @ResponseBody String getInventItemBarcodeData(@RequestBody String body, HttpServletRequest request) {

		InventItemBarcodeService inventItemBarcodeServices = (InventItemBarcodeService) context.getBean("inventItemBarcodeService");
		return new Gson().toJson(inventItemBarcodeServices.findAll());
	}

	@RequestMapping(value = "/uploadDocuments/", method = RequestMethod.POST/*, produces = "application/json", headers = {"Content-type=application/json"}*/)
	public @ResponseBody String getData(@RequestBody String body, HttpServletRequest request/*, HttpServletResponse response*/) {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		JSONObject json = null;
		String docs = request.getParameter("documents");
		String shop = request.getParameter("shopindex");

		/*try {
			json = (JSONObject)new JSONParser().parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String docs = (String) json.get("documents");
		String shop = (String) json.get("shopindex");*/

		Type type = new TypeToken<List<Document>>(){}.getType();
		List<Document> documents = new Gson().fromJson(docs, type);

		DocumentService documentService = (DocumentService) context.getBean("documentService");
		DocumentLineService documentLineService = (DocumentLineService) context.getBean("documentLineService");

		for(Document item : documents){

			DocumentHeader documentHeader = new DocumentHeader();
			documentHeader.setDocNum(item.getDocNum());
			documentHeader.setDocType(item.getDocType());
			documentHeader.setShopindex(shop);
			try {
				documentHeader.setDocDate(df.parse(item.getDocDate()));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
				documentService.save(documentHeader);

				if(documentLineService.search(item) != null){
					System.out.println("Line is existing");
				}

			if(item.getLines() != null){

				for(DocumentLines line : item.getLines()){
					documentLineService.save(line);
				}

			}
		}

		//System.out.println("-------------Procedure is finished SUCCESS----------------");
		return "SUCCESS";
	}

	/*@RequestMapping(value = "/sendData/", method = RequestMethod.POST, produces = "application/json", headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse getData(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = null;
		try {
			json = (JSONObject)new JSONParser().parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String results = (String) json.get("documents");

		Type type = new TypeToken<List<DocumentHeader>>(){}.getType();
		List<DocumentHeader> docList = new Gson().fromJson(results, type);

		for(DocumentHeader doc: docList){
			System.out.println("Request DocumentHeader: " + doc.getDocNum() + "," + doc.getDocType() + "," + doc.getDocType());
			if(doc.getLines() != null){

				for(DocumentLines line: doc.getLines())
					System.out.println("Line: " + line.getScu() + ", " + line.getBarcode() + ", " + line.getQty() + ", " + line.getDocRef());
			}
		}

		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/sendItem/", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse  getItem(@RequestBody String body, HttpServletRequest request) {

		System.out.println("Request come" + body);
		//String line = body.getScu();
		return new JsonResponse("OK","");
	}*/
	/*@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}


	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!yoyiuyiuyiu");
		return "helloWorld";
	}

	@RequestMapping(value = "/displayMessage/{msg}", method = RequestMethod.GET)
	public String displayMessage(@PathVariable String msg, ModelMap model) {
		model.addAttribute("msg", msg);
		System.out.println("Android request: " + msg);

		return "helloWorld";
	}*/
}