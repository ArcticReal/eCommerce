package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddProductPrice;
import com.skytala.eCommerce.command.UpdateProductPrice;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPriceAdded;
import com.skytala.eCommerce.event.ProductPriceUpdated;
import com.skytala.eCommerce.event.ProductPricesFound;
import com.skytala.eCommerce.query.FindProductPricesBy;
import com.skytala.eCommerce.query.FindProductPricesById;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<ProductPrice>> queryReturnVal = new HashMap<>();

	/**
	 * 
	 * @param productId
	 *            the product id of the product the price should be found of
	 * @param productPriceTypeId
	 *            the price type id
	 * @return a List with the ProductPrices
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/findBy" })
	public List<ProductPrice> findProductPricesBy(@RequestParam String productId,
			@RequestParam String productPriceTypeId) {

		FindProductPricesBy query = new FindProductPricesBy(productId, productPriceTypeId);

		int usedTicketId;

		synchronized (ProductController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPricesFound.class,
				event -> sendProductPricesFoundMessage(((ProductPricesFound) event).getFoundProductPrices(),
						usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	/**
	 * 
	 * 
	 * @param productId
	 *            Id of the product that you want to know the price of
	 * @return A list of different prices
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/findById" })
	public List<ProductPrice> findProductPricesById(@RequestParam String productId) {

		FindProductPricesById query = new FindProductPricesById(productId);

		int usedTicketId;

		synchronized (ProductController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPricesFound.class,
				event -> sendProductPricesFoundMessage(((ProductPricesFound) event).getFoundProductPrices(),
						usedTicketId));

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
			productPriceToBeAdded = ProductPriceMapper.map(request);
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

	@RequestMapping(method = RequestMethod.PUT, value = { "/update",
			"/change" }, consumes = "application/x-www-form-urlencoded")
	public boolean updateProductPrice(HttpServletRequest request) {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductPrice productPriceToBeUpdated = new ProductPrice();

		try {
			productPriceToBeUpdated = ProductPriceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return updateProductPrice(productPriceToBeUpdated);

	}

	public boolean updateProductPrice(ProductPrice productPriceToBeUpdated) {

		UpdateProductPrice com = new UpdateProductPrice(productPriceToBeUpdated);

		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceUpdated.class,
				event -> sendProductPriceChangedMessage(((ProductPriceUpdated) event).isSuccess(), usedTicketId));

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
