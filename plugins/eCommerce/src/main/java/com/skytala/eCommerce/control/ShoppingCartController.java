package com.skytala.eCommerce.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.List;
import com.skytala.eCommerce.entity.Product;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	@RequestMapping(method = RequestMethod.GET , value = "/show")
	public List<Product> show(){
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/x-www-form-urlencoded")
	public boolean addToCart(){
		
		
		return true;
	}
	@RequestMapping(method = RequestMethod.POST, value ="/remove", consumes = "application/x-www-form-urlencoded")
	public boolean removeFromCart(){
		
		
		return true;
	}

}
