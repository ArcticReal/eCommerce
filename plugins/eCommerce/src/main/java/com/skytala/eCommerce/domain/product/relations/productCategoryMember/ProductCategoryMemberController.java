package com.skytala.eCommerce.domain.product.relations.productCategoryMember;

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
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.command.AddProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.command.DeleteProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.command.UpdateProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.mapper.ProductCategoryMemberMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.query.FindProductCategoryMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productCategoryMembers")
public class ProductCategoryMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryMember
	 * @return a List with the ProductCategoryMembers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategoryMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryMembersBy query = new FindProductCategoryMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryMember> productCategoryMembers =((ProductCategoryMemberFound) Scheduler.execute(query).data()).getProductCategoryMembers();

		if (productCategoryMembers.size() == 1) {
			return ResponseEntity.ok().body(productCategoryMembers.get(0));
		}

		return ResponseEntity.ok().body(productCategoryMembers);

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
	public ResponseEntity<Object> createProductCategoryMember(HttpServletRequest request) throws Exception {

		ProductCategoryMember productCategoryMemberToBeAdded = new ProductCategoryMember();
		try {
			productCategoryMemberToBeAdded = ProductCategoryMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryMember(productCategoryMemberToBeAdded);

	}

	/**
	 * creates a new ProductCategoryMember entry in the ofbiz database
	 * 
	 * @param productCategoryMemberToBeAdded
	 *            the ProductCategoryMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryMember(@RequestBody ProductCategoryMember productCategoryMemberToBeAdded) throws Exception {

		AddProductCategoryMember command = new AddProductCategoryMember(productCategoryMemberToBeAdded);
		ProductCategoryMember productCategoryMember = ((ProductCategoryMemberAdded) Scheduler.execute(command).data()).getAddedProductCategoryMember();
		
		if (productCategoryMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryMember could not be created.");
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
	public boolean updateProductCategoryMember(HttpServletRequest request) throws Exception {

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

		ProductCategoryMember productCategoryMemberToBeUpdated = new ProductCategoryMember();

		try {
			productCategoryMemberToBeUpdated = ProductCategoryMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryMember(productCategoryMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryMember with the specific Id
	 * 
	 * @param productCategoryMemberToBeUpdated
	 *            the ProductCategoryMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryMember(@RequestBody ProductCategoryMember productCategoryMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryMemberToBeUpdated.setnull(null);

		UpdateProductCategoryMember command = new UpdateProductCategoryMember(productCategoryMemberToBeUpdated);

		try {
			if(((ProductCategoryMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryMemberId", productCategoryMemberId);
		try {

			Object foundProductCategoryMember = findProductCategoryMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryMemberId}")
	public ResponseEntity<Object> deleteProductCategoryMemberByIdUpdated(@PathVariable String productCategoryMemberId) throws Exception {
		DeleteProductCategoryMember command = new DeleteProductCategoryMember(productCategoryMemberId);

		try {
			if (((ProductCategoryMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryMember could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productCategoryMember/\" plus one of the following: "
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
