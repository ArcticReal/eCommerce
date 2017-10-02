package com.skytala.eCommerce.domain.productReview;

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
import com.skytala.eCommerce.domain.productReview.command.AddProductReview;
import com.skytala.eCommerce.domain.productReview.command.DeleteProductReview;
import com.skytala.eCommerce.domain.productReview.command.UpdateProductReview;
import com.skytala.eCommerce.domain.productReview.event.ProductReviewAdded;
import com.skytala.eCommerce.domain.productReview.event.ProductReviewDeleted;
import com.skytala.eCommerce.domain.productReview.event.ProductReviewFound;
import com.skytala.eCommerce.domain.productReview.event.ProductReviewUpdated;
import com.skytala.eCommerce.domain.productReview.mapper.ProductReviewMapper;
import com.skytala.eCommerce.domain.productReview.model.ProductReview;
import com.skytala.eCommerce.domain.productReview.query.FindProductReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productReviews")
public class ProductReviewController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<ProductReview>> queryReturnVal = new HashMap<>();
	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductReviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);

	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductReview
	 * @return a List with the ProductReviews
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductReviewsBy(@RequestParam Map<String, String> allRequestParams) {

		FindProductReviewsBy query = new FindProductReviewsBy(allRequestParams);

		int usedTicketId;

		synchronized (ProductReviewController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductReviewFound.class,
				event -> sendProductReviewsFoundMessage(((ProductReviewFound) event).getProductReviews(),
						usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return ResponseEntity.ok().body(queryReturnVal.remove(usedTicketId));

	}

	public void sendProductReviewsFoundMessage(List<ProductReview> productReviews, int usedTicketId) {
		queryReturnVal.put(usedTicketId, productReviews);
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
	public ResponseEntity<Object> createProductReview(HttpServletRequest request) {

		ProductReview productReviewToBeAdded = new ProductReview();
		try {
			productReviewToBeAdded = ProductReviewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductReview(productReviewToBeAdded);

	}

	/**
	 * creates a new ProductReview entry in the ofbiz database
	 * 
	 * @param productReviewToBeAdded
	 *            the ProductReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductReview(@RequestBody ProductReview productReviewToBeAdded) {

		AddProductReview com = new AddProductReview(productReviewToBeAdded);
		int usedTicketId;

		synchronized (ProductReviewController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductReviewAdded.class,
				event -> sendProductReviewChangedMessage(((ProductReviewAdded) event).isSuccess(), usedTicketId));

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

			return ResponseEntity.status(HttpStatus.CREATED).body("ProductReview was created successfully.");

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductReview could not be created.");

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
	public boolean updateProductReview(HttpServletRequest request) {

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

		ProductReview productReviewToBeUpdated = new ProductReview();

		try {
			productReviewToBeUpdated = ProductReviewMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductReview(productReviewToBeUpdated, productReviewToBeUpdated.getProductReviewId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductReview with the specific Id
	 * 
	 * @param productReviewToBeUpdated
	 *            the ProductReview thats to be updated
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productReviewId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductReview(@RequestBody ProductReview productReviewToBeUpdated,
			@PathVariable String productReviewId) {

		productReviewToBeUpdated.setProductReviewId(productReviewId);

		UpdateProductReview com = new UpdateProductReview(productReviewToBeUpdated);

		int usedTicketId;

		synchronized (ProductReviewController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductReviewUpdated.class,
				event -> sendProductReviewChangedMessage(((ProductReviewUpdated) event).isSuccess(), usedTicketId));

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
	 * removes a ProductReview from the database
	 * 
	 * @deprecated
	 * @param productReviewId:
	 *            the id of the ProductReview thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deleteproductReviewById(@RequestParam(value = "productReviewId") String productReviewId) {

		DeleteProductReview com = new DeleteProductReview(productReviewId);

		int usedTicketId;

		synchronized (ProductReviewController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductReviewDeleted.class,
				event -> sendProductReviewChangedMessage(((ProductReviewDeleted) event).isSuccess(), usedTicketId));

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

	public void sendProductReviewChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productReviewId}")
	public ResponseEntity<Object> findById(@PathVariable String productReviewId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productReviewId", productReviewId);
		try {

			ResponseEntity<Object> retVal = findProductReviewsBy(requestParams);
			return retVal;
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productReviewId}")
	public ResponseEntity<Object> deleteProductReviewByIdUpdated(@PathVariable String productReviewId) {
		DeleteProductReview com = new DeleteProductReview(productReviewId);

		int usedTicketId;

		synchronized (ProductReviewController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductReviewDeleted.class,
				event -> sendProductReviewChangedMessage(((ProductReviewDeleted) event).isSuccess(), usedTicketId));

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
		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductReview could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productReview/\" plus one of the following: "
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
