package com.kopo.c1;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.model.Model;

@RestController
public class ApiController {
//	@ResponseBody
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public HashMap<String, String> create(Locale locale, Model model) {
//		HashMap<String, String> data = new HashMap<>();
//		
//		DB db = new DB();
//		db.createTable();
//		
//		data.put("message", "테이블이 생성되었습니다.");
//		return data;
//	}
	
	@GetMapping(value = "/create")
	public HashMap<String, String> create(Locale locale, Model model) {
		HashMap<String, String> data = new HashMap<>();
		
		DB db = new DB();
		db.createTable();
		
		data.put("message", "테이블이 생성되었습니다.");
		return data;
	}
}
