package com.skytala.eCommerce.domain.product.relations.productStoreGroupMember;

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
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.command.AddProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.command.DeleteProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.command.UpdateProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event.ProductStoreGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event.ProductStoreGroupMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event.ProductStoreGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event.ProductStoreGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.mapper.ProductStoreGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.model.ProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.query.FindProductStoreGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreGroupMembers")
public class ProductStoreGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupMember
	 * @return a List with the ProductStoreGroupMembers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupMembersBy query = new FindProductStoreGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupMember> productStoreGroupMembers =((ProductStoreGroupMemberFound) Scheduler.execute(query).data()).getProductStoreGroupMembers();

		if (productStoreGroupMembers.size() == 1) {
			return ResponseEntity.ok().body(productStoreGroupMembers.get(0));
		}

		return ResponseEntity.ok().body(productStoreGroupMembers);

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
	public ResponseEntity<Object> createProductStoreGroupMember(HttpServletRequest request) throws Exception {

		ProductStoreGroupMember productStoreGroupMemberToBeAdded = new ProductStoreGroupMember();
		try {
			productStoreGroupMemberToBeAdded = ProductStoreGroupMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreGroupMember(productStoreGroupMemberToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroupMember entry in the ofbiz database
	 * 
	 * @param productStoreGroupMemberToBeAdded
	 *            the ProductStoreGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreGroupMember(@RequestBody ProductStoreGroupMember productStoreGroupMemberToBeAdded) throws Exception {

		AddProductStoreGroupMember command = new AddProductStoreGroupMember(productStoreGroupMemberToBeAdded);
		ProductStoreGroupMember productStoreGroupMember = ((ProductStoreGroupMemberAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupMember();
		
		if (productStoreGroupMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreGroupMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreGroupMember could not be created.");
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
	public boolean updateProductStoreGroupMember(HttpServletRequest request) throws Exception {

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

		ProductStoreGroupMember productStoreGroupMemberToBeUpdated = new ProductStoreGroupMember();

		try {
			productStoreGroupMemberToBeUpdated = ProductStoreGroupMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreGroupMember(productStoreGroupMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreGroupMember with the specific Id
	 * 
	 * @param productStoreGroupMemberToBeUpdated
	 *            the ProductStoreGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreGroupMember(@RequestBody ProductStoreGroupMember productStoreGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreGroupMemberToBeUpdated.setnull(null);

		UpdateProductStoreGroupMember command = new UpdateProductStoreGroupMember(productStoreGroupMemberToBeUpdated);

		try {
			if(((ProductStoreGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreGroupMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupMemberId", productStoreGroupMemberId);
		try {

			Object foundProductStoreGroupMember = findProductStoreGroupMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreGroupMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreGroupMemberId}")
	public ResponseEntity<Object> deleteProductStoreGroupMemberByIdUpdated(@PathVariable String productStoreGroupMemberId) throws Exception {
		DeleteProductStoreGroupMember command = new DeleteProductStoreGroupMember(productStoreGroupMemberId);

		try {
			if (((ProductStoreGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreGroupMember could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStoreGroupMember/\" plus one of the following: "
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
