package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.NewOrder;
import com.skytala.eCommerce.entity.ShoppingCart;
import com.skytala.eCommerce.event.OrderCreated;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	private static int requestTicketId = 0;

	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();

	@RequestMapping("/create")
	public boolean createOrder(HttpSession session) {

		ShoppingCart sc = new ShoppingCart();
		if (session.getAttribute("cart") != null) {
			sc = (ShoppingCart) session.getAttribute("cart");
		}else {
			return false;
		}

		NewOrder order = new NewOrder(sc);
		int usedTicketId;

		synchronized (OrderController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}

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