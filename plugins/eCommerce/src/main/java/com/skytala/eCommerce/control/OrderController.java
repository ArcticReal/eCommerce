package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.NewOrder;
import com.skytala.eCommerce.entity.Order;
import com.skytala.eCommerce.entity.OrderHeader;
import com.skytala.eCommerce.entity.ShoppingCart;
import com.skytala.eCommerce.event.OrderCreated;
import com.skytala.eCommerce.event.OrdersFound;
import com.skytala.eCommerce.event.SpecificOrderFound;
import com.skytala.eCommerce.query.FindAllOrders;
import com.skytala.eCommerce.query.FindOrderById;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	private static int requestTicketId = 0;

	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<OrderHeader>> orderHeaderReturnVal = new HashMap<>();
	private static Map<Integer, Order> specificOrderReturnVal = new HashMap<>();

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public boolean createOrder(HttpSession session) {

		ShoppingCart sc = new ShoppingCart();
		if (session.getAttribute("cart") != null) {
			sc = (ShoppingCart) session.getAttribute("cart");
		} else {
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

		try {
			Scheduler.instance().schedule(order).executeNext();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}

		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		boolean orderSuccess = commandReturnVal.remove(usedTicketId);
		if (orderSuccess) {
			session.removeAttribute("cart");
		}

		return orderSuccess;

	}

	public void sendOrderCreatedMessage(boolean b, int id) {
		commandReturnVal.put(id, b);
	}

	@RequestMapping(method = RequestMethod.GET, value = {"/showAll", "/getAll"})
	public List<OrderHeader> getAllOrders() {
		FindAllOrders query = new FindAllOrders();

		int usedTicketId;

		synchronized (OrderController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}

		Broker.instance().subscribe(OrdersFound.class,
				event -> sendOrdersFoundMessage(((OrdersFound) event).getOrders(), usedTicketId));

		query.execute();

		while (!orderHeaderReturnVal.containsKey(usedTicketId)) {
		}

		return orderHeaderReturnVal.remove(usedTicketId);
	}

	public void sendOrdersFoundMessage(List<OrderHeader> orders, int usedTicketId) {
		orderHeaderReturnVal.put(usedTicketId, orders);

	}

	
	@RequestMapping(method = RequestMethod.GET, value = {"/showSpecific", "/getSpecific"})
	public Order getSpecificOrder(@RequestParam("orderId") String orderId) {

		FindOrderById query = new FindOrderById(orderId);

		int usedTicketId;

		synchronized (OrderController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}

		Broker.instance().subscribe(SpecificOrderFound.class,
				event -> sendSpecificOrderFoundMessage(((SpecificOrderFound) event).getOrder(), usedTicketId));

		query.execute();

		while (!specificOrderReturnVal.containsKey(usedTicketId)) {
		}

		Order order = specificOrderReturnVal.remove(usedTicketId);
		
		return order;
	}

	public void sendSpecificOrderFoundMessage(Order order, int usedTicketId) {
		specificOrderReturnVal.put(usedTicketId, order);

	}

}