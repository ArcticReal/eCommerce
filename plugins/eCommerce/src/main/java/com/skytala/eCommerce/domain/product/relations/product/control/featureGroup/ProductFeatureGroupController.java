package com.skytala.eCommerce.domain.product.relations.product.control.featureGroup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.AddProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.DeleteProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.UpdateProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroup.ProductFeatureGroupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.query.featureGroup.FindProductFeatureGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productFeatureGroups")
public class ProductFeatureGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureGroup
	 * @return a List with the ProductFeatureGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureGroupsBy query = new FindProductFeatureGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureGroup> productFeatureGroups =((ProductFeatureGroupFound) Scheduler.execute(query).data()).getProductFeatureGroups();

		if (productFeatureGroups.size() == 1) {
			return ResponseEntity.ok().body(productFeatureGroups.get(0));
		}

		return ResponseEntity.ok().body(productFeatureGroups);

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
	public ResponseEntity<Object> createProductFeatureGroup(HttpServletRequest request) throws Exception {

		ProductFeatureGroup productFeatureGroupToBeAdded = new ProductFeatureGroup();
		try {
			productFeatureGroupToBeAdded = ProductFeatureGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureGroup(productFeatureGroupToBeAdded);

	}

	/**
	 * creates a new ProductFeatureGroup entry in the ofbiz database
	 * 
	 * @param productFeatureGroupToBeAdded
	 *            the ProductFeatureGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureGroup(@RequestBody ProductFeatureGroup productFeatureGroupToBeAdded) throws Exception {

		AddProductFeatureGroup command = new AddProductFeatureGroup(productFeatureGroupToBeAdded);
		ProductFeatureGroup productFeatureGroup = ((ProductFeatureGroupAdded) Scheduler.execute(command).data()).getAddedProductFeatureGroup();
		
		if (productFeatureGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureGroup could not be created.");
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
	public boolean updateProductFeatureGroup(HttpServletRequest request) throws Exception {

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

		ProductFeatureGroup productFeatureGroupToBeUpdated = new ProductFeatureGroup();

		try {
			productFeatureGroupToBeUpdated = ProductFeatureGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureGroup(productFeatureGroupToBeUpdated, productFeatureGroupToBeUpdated.getProductFeatureGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureGroup with the specific Id
	 * 
	 * @param productFeatureGroupToBeUpdated
	 *            the ProductFeatureGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureGroup(@RequestBody ProductFeatureGroup productFeatureGroupToBeUpdated,
			@PathVariable String productFeatureGroupId) throws Exception {

		productFeatureGroupToBeUpdated.setProductFeatureGroupId(productFeatureGroupId);

		UpdateProductFeatureGroup command = new UpdateProductFeatureGroup(productFeatureGroupToBeUpdated);

		try {
			if(((ProductFeatureGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureGroupId", productFeatureGroupId);
		try {

			Object foundProductFeatureGroup = findProductFeatureGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureGroupId}")
	public ResponseEntity<Object> deleteProductFeatureGroupByIdUpdated(@PathVariable String productFeatureGroupId) throws Exception {
		DeleteProductFeatureGroup command = new DeleteProductFeatureGroup(productFeatureGroupId);

		try {
			if (((ProductFeatureGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureGroup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureGroup/\" plus one of the following: "
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
