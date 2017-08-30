package com.skytala.eCommerce.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.AddProduct;
import com.skytala.eCommerce.events.ProductAdded;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	//TODO: requests
	@RequestMapping("/broker")
	public void brokerTest() {
		
		Broker.instance().subscribe(ProductAdded.class, 
				event -> sendProductAddedMessage());
		
		try {
			Scheduler.instance().schedule(new AddProduct()).executeNext();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public void sendProductAddedMessage() {
		System.out.println("Product added!\n\n\n\n\n\n");
		
		Broker.instance().unsubscribe(ProductAdded.class,
				event -> sendProductAddedMessage());
	}
}
