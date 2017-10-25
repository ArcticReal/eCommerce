package com.skytala.eCommerce.domain.product.relations.prodCatalog.control.category;

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
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.AddProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.DeleteProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.UpdateProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.category.ProdCatalogCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.query.category.FindProdCatalogCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/prodCatalogCategorys")
public class ProdCatalogCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalogCategory
	 * @return a List with the ProdCatalogCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProdCatalogCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogCategorysBy query = new FindProdCatalogCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogCategory> prodCatalogCategorys =((ProdCatalogCategoryFound) Scheduler.execute(query).data()).getProdCatalogCategorys();

		if (prodCatalogCategorys.size() == 1) {
			return ResponseEntity.ok().body(prodCatalogCategorys.get(0));
		}

		return ResponseEntity.ok().body(prodCatalogCategorys);

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
	public ResponseEntity<Object> createProdCatalogCategory(HttpServletRequest request) throws Exception {

		ProdCatalogCategory prodCatalogCategoryToBeAdded = new ProdCatalogCategory();
		try {
			prodCatalogCategoryToBeAdded = ProdCatalogCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProdCatalogCategory(prodCatalogCategoryToBeAdded);

	}

	/**
	 * creates a new ProdCatalogCategory entry in the ofbiz database
	 * 
	 * @param prodCatalogCategoryToBeAdded
	 *            the ProdCatalogCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProdCatalogCategory(@RequestBody ProdCatalogCategory prodCatalogCategoryToBeAdded) throws Exception {

		AddProdCatalogCategory command = new AddProdCatalogCategory(prodCatalogCategoryToBeAdded);
		ProdCatalogCategory prodCatalogCategory = ((ProdCatalogCategoryAdded) Scheduler.execute(command).data()).getAddedProdCatalogCategory();
		
		if (prodCatalogCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodCatalogCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdCatalogCategory could not be created.");
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
	public boolean updateProdCatalogCategory(HttpServletRequest request) throws Exception {

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

		ProdCatalogCategory prodCatalogCategoryToBeUpdated = new ProdCatalogCategory();

		try {
			prodCatalogCategoryToBeUpdated = ProdCatalogCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdCatalogCategory(prodCatalogCategoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProdCatalogCategory with the specific Id
	 * 
	 * @param prodCatalogCategoryToBeUpdated
	 *            the ProdCatalogCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProdCatalogCategory(@RequestBody ProdCatalogCategory prodCatalogCategoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodCatalogCategoryToBeUpdated.setnull(null);

		UpdateProdCatalogCategory command = new UpdateProdCatalogCategory(prodCatalogCategoryToBeUpdated);

		try {
			if(((ProdCatalogCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{prodCatalogCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String prodCatalogCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogCategoryId", prodCatalogCategoryId);
		try {

			Object foundProdCatalogCategory = findProdCatalogCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdCatalogCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{prodCatalogCategoryId}")
	public ResponseEntity<Object> deleteProdCatalogCategoryByIdUpdated(@PathVariable String prodCatalogCategoryId) throws Exception {
		DeleteProdCatalogCategory command = new DeleteProdCatalogCategory(prodCatalogCategoryId);

		try {
			if (((ProdCatalogCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdCatalogCategory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/prodCatalogCategory/\" plus one of the following: "
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
