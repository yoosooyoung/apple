package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@RequestMapping("/hello")
	public String index() {
		return "Hello World1";
	}

	@RequestMapping("/join")
	@ResponseBody
	public List<Map<String,String>> index1() {
		List<Map<String,String>>test = new ArrayList<Map<String,String>>();
		
		Map<String, String> test1 = new HashMap<>();
		test1.put("1", "유수영");
		test1.put("2", "장현정");
		
		test.add(test1);
		
		
		return test;
	}
	
	
}
