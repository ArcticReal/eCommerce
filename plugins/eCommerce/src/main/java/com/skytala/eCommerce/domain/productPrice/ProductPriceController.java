package com.skytala.eCommerce.domain.productPrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.productPrice.command.AddProductPrice;
import com.skytala.eCommerce.domain.productPrice.command.DeleteProductPrice;
import com.skytala.eCommerce.domain.productPrice.command.UpdateProductPrice;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceAdded;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceDeleted;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceFound;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceUpdated;
import com.skytala.eCommerce.domain.productPrice.mapper.ProductPriceMapper;
import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
import com.skytala.eCommerce.domain.productPrice.query.FindProductPricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPrices")
public class ProductPriceController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<ProductPrice>> queryReturnVal = new HashMap<>();
	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);

	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPrice
	 * @return a List with the ProductPrices
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPricesBy(@RequestParam Map<String, String> allRequestParams) {

		FindProductPricesBy query = new FindProductPricesBy(allRequestParams);

		int usedTicketId;

		synchronized (ProductPriceController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceFound.class,
				event -> sendProductPricesFoundMessage(((ProductPriceFound) event).getProductPrices(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return ResponseEntity.ok().body(queryReturnVal.remove(usedTicketId));

	}

	public void sendProductPricesFoundMessage(List<ProductPrice> productPrices, int usedTicketId) {
		queryReturnVal.put(usedTicketId, productPrices);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductPrice(HttpServletRequest request) {

		ProductPrice productPriceToBeAdded = new ProductPrice();
		try {
			productPriceToBeAdded = ProductPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPrice(productPriceToBeAdded);

	}

	/**
	 * creates a new ProductPrice entry in the ofbiz database
	 * 
	 * @param productPriceToBeAdded
	 *            the ProductPrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPrice(@RequestBody ProductPrice productPriceToBeAdded) {

		AddProductPrice command = new AddProductPrice(productPriceToBeAdded);
		
		int usedTicketId;
		synchronized (ProductPriceController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		
		// Success-Handler for adding a product
		Broker.instance().subscribe(ProductPriceAdded.class,
				event -> sendProductPriceChangedMessage(((ProductPriceAdded) event).isSuccess(), usedTicketId));
	
		
		try {
			// Add the product to the database (command pattern!)
			Scheduler.instance().schedule(command).executeNext();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						         .body("An Error ocuured while processing Command!");
		}
		
		
		while (!commandReturnVal.containsKey(usedTicketId)) {}

		
		if (commandReturnVal.remove(usedTicketId))
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body("ProductPrice was created successfully.");
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body("ProductPrice could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

		return false;

	}

	/**
	 * Updates the ProductPrice with the specific Id
	 * 
	 * @param productPriceToBeUpdated
	 *            the ProductPrice thats to be updated
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPrice(@RequestBody ProductPrice productPriceToBeUpdated,
			@PathVariable String productPriceId) {

		// productPriceToBeUpdated.setProductPriceId(productPriceId);

		UpdateProductPrice com = new UpdateProductPrice(productPriceToBeUpdated);

		int usedTicketId;

		synchronized (ProductPriceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceUpdated.class,
				event -> sendProductPriceChangedMessage(((ProductPriceUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occured");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (commandReturnVal.remove(usedTicketId)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	/**
	 * removes a ProductPrice from the database
	 * 
	 * @deprecated
	 * @param productPriceId:
	 *            the id of the ProductPrice thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deleteproductPriceById(@RequestParam(value = "productPriceId") String productPriceId) {

		DeleteProductPrice com = new DeleteProductPrice(productPriceId);

		int usedTicketId;

		synchronized (ProductPriceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceDeleted.class,
				event -> sendProductPriceChangedMessage(((ProductPriceDeleted) event).isSuccess(), usedTicketId));

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

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceId", productPriceId);
		try {

			ResponseEntity<Object> retVal = findProductPricesBy(requestParams);
			return retVal;
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceId}")
	public ResponseEntity<Object> deleteProductPriceByIdUpdated(@PathVariable String productPriceId) {
		DeleteProductPrice com = new DeleteProductPrice(productPriceId);

		int usedTicketId;

		synchronized (ProductPriceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductPriceDeleted.class,
				event -> sendProductPriceChangedMessage(((ProductPriceDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (commandReturnVal.remove(usedTicketId)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPrice could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPrice/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
