package com.springapp.mvc;


import com.dct.server.model.DocumentLines;
import com.dct.server.model.Documnent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
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
	}

	@RequestMapping(value = "/sendData/", method = RequestMethod.POST, produces = "application/json", headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse getData(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = null;
		try {
			json = (JSONObject)new JSONParser().parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String results = (String) json.get("documents");

		Type type = new TypeToken<List<Documnent>>(){}.getType();
		List<Documnent> docList = new Gson().fromJson(results, type);

		for(Documnent doc: docList){
			System.out.println("Request Document: " + doc.getDocNum() + "," + doc.getDocType() + "," + doc.getDocType());
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
	}

}