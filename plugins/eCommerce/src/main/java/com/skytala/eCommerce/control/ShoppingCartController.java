package com.skytala.eCommerce.control;


import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.entity.Position;
import com.skytala.eCommerce.entity.ShoppingCart;






@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	@RequestMapping(method = RequestMethod.GET , value = "/show")
	public LinkedList<Position> show(HttpSession session){
	
		ShoppingCart sc = new ShoppingCart();
		if(session.getAttribute("cart")!=null){
			sc = (ShoppingCart) session.getAttribute("cart");
			return sc.getPositions();
		}
		return null;
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/x-www-form-urlencoded")
	public boolean addToCart(HttpSession session){
	
		if(session.getAttribute("cart") == null){
			
			ShoppingCart sc = new ShoppingCart();
			session.setAttribute("cart",sc);

			return true;
		}
		
		
		
		return false;
	}
	@RequestMapping(method = RequestMethod.POST, value ="/remove", consumes = "application/x-www-form-urlencoded")
	public boolean removeFromCart(HttpSession session){
		
		
		return true;
	}

}
