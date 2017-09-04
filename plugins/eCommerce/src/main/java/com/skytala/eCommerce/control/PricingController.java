package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.AddProductPrice;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.event.ProductPriceAdded;
import com.skytala.eCommerce.event.ProductPricesFound;
import com.skytala.eCommerce.query.FindProductPricesBy;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<ProductPrice>> queryReturnVal = new HashMap<>();

	/**
	 * 
	 * 
	 * @param productId
	 *            Id of the product that you want to know the price of
	 * @return A list of different prices
	 */
	@RequestMapping()
	public List<ProductPrice> findProductPricesBy(@RequestParam String productId) {

		FindProductPricesBy query = new FindProductPricesBy(productId);

		int usedTicketId;

		synchronized (ProductController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPricesFound.class,
				event -> sendProductPricesFoundMessage(((ProductPricesFound) event).getFoundProductPrices(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}
	
	public void sendProductPricesFoundMessage(List<ProductPrice> prices, int usedTicketId) {
		queryReturnVal.put(usedTicketId, prices);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/create",
			"/insert" }, consumes = "application/x-www-form-urlencoded")
	public boolean createProductPrice(HttpServletRequest request) {

		ProductPrice productPriceToBeAdded = new ProductPrice();
		try {
			// TODO: mapper: productPrioceToBeAdded = ProductPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}

		return this.createProductPrice(productPriceToBeAdded);

	}

	/**
	 * creates a new ProductPrice entry in the ofbiz database
	 * 
	 * @param productPriceToBeAdded
	 *            the product price thats to be added
	 * @return true on success; false on fail
	 */
	public boolean createProductPrice(ProductPrice productPriceToBeAdded) {

		AddProductPrice com = new AddProductPrice(productPriceToBeAdded);
		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceAdded.class,
				event -> sendProductPriceChangedMessage(((ProductPriceAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	public void sendProductPriceChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

}
