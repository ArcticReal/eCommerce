package com.skytala.eCommerce.domain.product.relations.productCategoryLink;

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
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.command.AddProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.command.DeleteProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.command.UpdateProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.event.ProductCategoryLinkAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.event.ProductCategoryLinkDeleted;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.event.ProductCategoryLinkFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.event.ProductCategoryLinkUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.mapper.ProductCategoryLinkMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.query.FindProductCategoryLinksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productCategoryLinks")
public class ProductCategoryLinkController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryLinkController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryLink
	 * @return a List with the ProductCategoryLinks
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategoryLinksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryLinksBy query = new FindProductCategoryLinksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryLink> productCategoryLinks =((ProductCategoryLinkFound) Scheduler.execute(query).data()).getProductCategoryLinks();

		if (productCategoryLinks.size() == 1) {
			return ResponseEntity.ok().body(productCategoryLinks.get(0));
		}

		return ResponseEntity.ok().body(productCategoryLinks);

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
	public ResponseEntity<Object> createProductCategoryLink(HttpServletRequest request) throws Exception {

		ProductCategoryLink productCategoryLinkToBeAdded = new ProductCategoryLink();
		try {
			productCategoryLinkToBeAdded = ProductCategoryLinkMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryLink(productCategoryLinkToBeAdded);

	}

	/**
	 * creates a new ProductCategoryLink entry in the ofbiz database
	 * 
	 * @param productCategoryLinkToBeAdded
	 *            the ProductCategoryLink thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryLink(@RequestBody ProductCategoryLink productCategoryLinkToBeAdded) throws Exception {

		AddProductCategoryLink command = new AddProductCategoryLink(productCategoryLinkToBeAdded);
		ProductCategoryLink productCategoryLink = ((ProductCategoryLinkAdded) Scheduler.execute(command).data()).getAddedProductCategoryLink();
		
		if (productCategoryLink != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryLink);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryLink could not be created.");
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
	public boolean updateProductCategoryLink(HttpServletRequest request) throws Exception {

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

		ProductCategoryLink productCategoryLinkToBeUpdated = new ProductCategoryLink();

		try {
			productCategoryLinkToBeUpdated = ProductCategoryLinkMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryLink(productCategoryLinkToBeUpdated, productCategoryLinkToBeUpdated.getLinkSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryLink with the specific Id
	 * 
	 * @param productCategoryLinkToBeUpdated
	 *            the ProductCategoryLink thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{linkSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryLink(@RequestBody ProductCategoryLink productCategoryLinkToBeUpdated,
			@PathVariable String linkSeqId) throws Exception {

		productCategoryLinkToBeUpdated.setLinkSeqId(linkSeqId);

		UpdateProductCategoryLink command = new UpdateProductCategoryLink(productCategoryLinkToBeUpdated);

		try {
			if(((ProductCategoryLinkUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryLinkId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryLinkId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryLinkId", productCategoryLinkId);
		try {

			Object foundProductCategoryLink = findProductCategoryLinksBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryLink);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryLinkId}")
	public ResponseEntity<Object> deleteProductCategoryLinkByIdUpdated(@PathVariable String productCategoryLinkId) throws Exception {
		DeleteProductCategoryLink command = new DeleteProductCategoryLink(productCategoryLinkId);

		try {
			if (((ProductCategoryLinkDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryLink could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productCategoryLink/\" plus one of the following: "
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