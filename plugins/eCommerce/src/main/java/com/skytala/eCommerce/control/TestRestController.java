package com.skytala.eCommerce.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {

	@RequestMapping("/test")
	public void test() {
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		System.out.println("hallo!!!");
		
	}
	
}
