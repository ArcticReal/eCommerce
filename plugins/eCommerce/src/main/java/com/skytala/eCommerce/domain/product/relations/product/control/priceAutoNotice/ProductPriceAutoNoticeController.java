package com.skytala.eCommerce.domain.product.relations.product.control.priceAutoNotice;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.AddProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.DeleteProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.UpdateProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAutoNotice.ProductPriceAutoNoticeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.query.priceAutoNotice.FindProductPriceAutoNoticesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPriceAutoNotices")
public class ProductPriceAutoNoticeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceAutoNoticeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceAutoNotice
	 * @return a List with the ProductPriceAutoNotices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPriceAutoNoticesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceAutoNoticesBy query = new FindProductPriceAutoNoticesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceAutoNotice> productPriceAutoNotices =((ProductPriceAutoNoticeFound) Scheduler.execute(query).data()).getProductPriceAutoNotices();

		if (productPriceAutoNotices.size() == 1) {
			return ResponseEntity.ok().body(productPriceAutoNotices.get(0));
		}

		return ResponseEntity.ok().body(productPriceAutoNotices);

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
	public ResponseEntity<Object> createProductPriceAutoNotice(HttpServletRequest request) throws Exception {

		ProductPriceAutoNotice productPriceAutoNoticeToBeAdded = new ProductPriceAutoNotice();
		try {
			productPriceAutoNoticeToBeAdded = ProductPriceAutoNoticeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceAutoNotice(productPriceAutoNoticeToBeAdded);

	}

	/**
	 * creates a new ProductPriceAutoNotice entry in the ofbiz database
	 * 
	 * @param productPriceAutoNoticeToBeAdded
	 *            the ProductPriceAutoNotice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceAutoNotice(@RequestBody ProductPriceAutoNotice productPriceAutoNoticeToBeAdded) throws Exception {

		AddProductPriceAutoNotice command = new AddProductPriceAutoNotice(productPriceAutoNoticeToBeAdded);
		ProductPriceAutoNotice productPriceAutoNotice = ((ProductPriceAutoNoticeAdded) Scheduler.execute(command).data()).getAddedProductPriceAutoNotice();
		
		if (productPriceAutoNotice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceAutoNotice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceAutoNotice could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductPriceAutoNotice(HttpServletRequest request) throws Exception {

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

		ProductPriceAutoNotice productPriceAutoNoticeToBeUpdated = new ProductPriceAutoNotice();

		try {
			productPriceAutoNoticeToBeUpdated = ProductPriceAutoNoticeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceAutoNotice(productPriceAutoNoticeToBeUpdated, productPriceAutoNoticeToBeUpdated.getProductPriceNoticeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPriceAutoNotice with the specific Id
	 * 
	 * @param productPriceAutoNoticeToBeUpdated
	 *            the ProductPriceAutoNotice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceNoticeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPriceAutoNotice(@RequestBody ProductPriceAutoNotice productPriceAutoNoticeToBeUpdated,
			@PathVariable String productPriceNoticeId) throws Exception {

		productPriceAutoNoticeToBeUpdated.setProductPriceNoticeId(productPriceNoticeId);

		UpdateProductPriceAutoNotice command = new UpdateProductPriceAutoNotice(productPriceAutoNoticeToBeUpdated);

		try {
			if(((ProductPriceAutoNoticeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceAutoNoticeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceAutoNoticeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceAutoNoticeId", productPriceAutoNoticeId);
		try {

			Object foundProductPriceAutoNotice = findProductPriceAutoNoticesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceAutoNotice);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceAutoNoticeId}")
	public ResponseEntity<Object> deleteProductPriceAutoNoticeByIdUpdated(@PathVariable String productPriceAutoNoticeId) throws Exception {
		DeleteProductPriceAutoNotice command = new DeleteProductPriceAutoNotice(productPriceAutoNoticeId);

		try {
			if (((ProductPriceAutoNoticeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceAutoNotice could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPriceAutoNotice/\" plus one of the following: "
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
