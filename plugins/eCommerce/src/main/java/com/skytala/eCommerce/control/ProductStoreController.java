package com.skytala.eCommerce.control;

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
import com.skytala.eCommerce.command.AddProductStore;
import com.skytala.eCommerce.command.DeleteProductStore;
import com.skytala.eCommerce.command.UpdateProductStore;
import com.skytala.eCommerce.entity.ProductStore;
import com.skytala.eCommerce.entity.ProductStoreMapper;
import com.skytala.eCommerce.event.ProductStoreAdded;
import com.skytala.eCommerce.event.ProductStoreDeleted;
import com.skytala.eCommerce.event.ProductStoreFound;
import com.skytala.eCommerce.event.ProductStoreUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.query.FindProductStoresBy;

@RestController
@RequestMapping("/productStores")
public class ProductStoreController {

	private static int requestTicketId = 0;
	private static Map<Integer, Event> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<ProductStore>> queryReturnVal = new HashMap<>();
	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);

	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStore
	 * @return a List with the ProductStores
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoresBy(
			@RequestParam(required = false) Map<String, String> allRequestParams) {

		FindProductStoresBy query = new FindProductStoresBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		int usedTicketId;

		synchronized (ProductStoreController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductStoreFound.class,
				event -> sendProductStoresFoundMessage(((ProductStoreFound) event).getProductStores(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}

		List<ProductStore> productStores = queryReturnVal.remove(usedTicketId);

		if (productStores.size() == 1) {
			return ResponseEntity.ok().body(productStores.get(0));
		}
		return ResponseEntity.ok().body(productStores);

	}

	public void sendProductStoresFoundMessage(List<ProductStore> productStores, int usedTicketId) {
		queryReturnVal.put(usedTicketId, productStores);
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
	public ResponseEntity<Object> createProductStore(HttpServletRequest request) {

		ProductStore productStoreToBeAdded = new ProductStore();
		try {
			productStoreToBeAdded = ProductStoreMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStore(productStoreToBeAdded);

	}

	/**
	 * creates a new ProductStore entry in the ofbiz database
	 * 
	 * @param productStoreToBeAdded
	 *            the ProductStore thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStore(@RequestBody ProductStore productStoreToBeAdded) {

		AddProductStore com = new AddProductStore(productStoreToBeAdded);
		int usedTicketId;

		synchronized (ProductStoreController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductStoreAdded.class,
				event -> sendProductStoreChangedMessage(((ProductStoreAdded) event), usedTicketId));

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

		ProductStoreAdded event = (ProductStoreAdded) commandReturnVal.remove(usedTicketId);
		if (event.isSuccess()) {

			return ResponseEntity.status(HttpStatus.CREATED).body(event.getAddedProductStore());

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStore could not be created.");

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
	public boolean updateProductStore(HttpServletRequest request) {

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

		ProductStore productStoreToBeUpdated = new ProductStore();

		try {
			productStoreToBeUpdated = ProductStoreMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStore(productStoreToBeUpdated, productStoreToBeUpdated.getProductStoreId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStore with the specific Id
	 * 
	 * @param productStoreToBeUpdated
	 *            the ProductStore thats to be updated
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStore(@RequestBody ProductStore productStoreToBeUpdated,
			@PathVariable String productStoreId) {

		productStoreToBeUpdated.setProductStoreId(productStoreId);

		UpdateProductStore com = new UpdateProductStore(productStoreToBeUpdated);

		int usedTicketId;

		synchronized (ProductStoreController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductStoreUpdated.class,
				event -> sendProductStoreChangedMessage(((ProductStoreUpdated) event), usedTicketId));

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

		if (((ProductStoreUpdated) commandReturnVal.remove(usedTicketId)).isSuccess()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	/**
	 * removes a ProductStore from the database
	 * 
	 * @deprecated
	 * @param productStoreId:
	 *            the id of the ProductStore thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deleteproductStoreById(@RequestParam(value = "productStoreId") String productStoreId) {

		DeleteProductStore com = new DeleteProductStore(productStoreId);

		int usedTicketId;

		synchronized (ProductStoreController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductStoreDeleted.class,
				event -> sendProductStoreChangedMessage(((ProductStoreDeleted) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return ((ProductStoreDeleted) commandReturnVal.remove(usedTicketId)).isSuccess();
	}

	public void sendProductStoreChangedMessage(Event event, int usedTicketId) {
		commandReturnVal.put(usedTicketId, event);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreId", productStoreId);
		try {

			Object foundProductStore = findProductStoresBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStore);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreId}")
	public ResponseEntity<Object> deleteProductStoreByIdUpdated(@PathVariable String productStoreId) {
		DeleteProductStore com = new DeleteProductStore(productStoreId);

		int usedTicketId;

		synchronized (ProductStoreController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductStoreDeleted.class,
				event -> sendProductStoreChangedMessage(((ProductStoreDeleted) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (((ProductStoreDeleted) commandReturnVal.remove(usedTicketId)).isSuccess()) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStore could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStore/\" plus one of the following: "
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
