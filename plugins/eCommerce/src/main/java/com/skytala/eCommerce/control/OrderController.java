package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.NewOrder;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ShoppingCart;
import com.skytala.eCommerce.event.OrderCreated;
import com.skytala.eCommerce.event.ProductAdded;
import com.skytala.eCommerce.event.ProductsFound;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static int requestTicketId = 0;
	
	private static Map<Integer , Boolean> commandReturnVal = new HashMap<>();

	@RequestMapping("/create")
	public Boolean createOrder(HttpSession session) {

		/*
		 * Delegator delegator = DelegatorFactory.getDelegator("default"); try {
		 * Map<String, String> map = new HashMap<>(); map.put("uomTypeId",
		 * "CURRENCY_MEASURE"); map.put("description", "Euro");
		 * System.out.println(delegator.findByAnd("Uom", map, null,
		 * false)+"\n\n"); } catch (GenericEntityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
//		 */
//		ShoppingCart sc = new ShoppingCart();
//		if (session.getAttribute("cart") != null){
//			sc = (ShoppingCart) session.getAttribute("cart");
//		} else {
//			System.out.println("Error keine gueltige Session");
//		}
		ShoppingCart sc = new ShoppingCart();
		if(session.getAttribute("cart")!=null){
			sc = (ShoppingCart) session.getAttribute("cart");
		}
//		
		NewOrder order = new NewOrder(sc);
		int usedTicketId;
		
		synchronized(OrderController.class){
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
//		Broker.instance().subscribe(ProductAdded.class,event -> sendProductChangedMessage(((ProductAdded) event).isSuccess(), usedTicketId));
		Broker.instance().subscribe(OrderCreated.class,
				event -> sendOrderCreatedMessage(((OrderCreated) event).isSuccess(), usedTicketId));
		
		order.execute();
		
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}
		return commandReturnVal.remove(usedTicketId);

		
		
	}
	
	public void sendOrderCreatedMessage(boolean b, int id) {
		commandReturnVal.put(id, b);
	}
	
}