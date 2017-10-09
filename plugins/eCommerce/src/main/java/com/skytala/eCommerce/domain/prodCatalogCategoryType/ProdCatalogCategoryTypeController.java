package com.skytala.eCommerce.domain.prodCatalogCategoryType;

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
import com.skytala.eCommerce.domain.prodCatalogCategoryType.command.AddProdCatalogCategoryType;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.command.DeleteProdCatalogCategoryType;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.command.UpdateProdCatalogCategoryType;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.event.ProdCatalogCategoryTypeAdded;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.event.ProdCatalogCategoryTypeDeleted;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.event.ProdCatalogCategoryTypeFound;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.event.ProdCatalogCategoryTypeUpdated;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.mapper.ProdCatalogCategoryTypeMapper;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.model.ProdCatalogCategoryType;
import com.skytala.eCommerce.domain.prodCatalogCategoryType.query.FindProdCatalogCategoryTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/prodCatalogCategoryTypes")
public class ProdCatalogCategoryTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogCategoryTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalogCategoryType
	 * @return a List with the ProdCatalogCategoryTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProdCatalogCategoryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogCategoryTypesBy query = new FindProdCatalogCategoryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogCategoryType> prodCatalogCategoryTypes =((ProdCatalogCategoryTypeFound) Scheduler.execute(query).data()).getProdCatalogCategoryTypes();

		if (prodCatalogCategoryTypes.size() == 1) {
			return ResponseEntity.ok().body(prodCatalogCategoryTypes.get(0));
		}

		return ResponseEntity.ok().body(prodCatalogCategoryTypes);

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
	public ResponseEntity<Object> createProdCatalogCategoryType(HttpServletRequest request) throws Exception {

		ProdCatalogCategoryType prodCatalogCategoryTypeToBeAdded = new ProdCatalogCategoryType();
		try {
			prodCatalogCategoryTypeToBeAdded = ProdCatalogCategoryTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProdCatalogCategoryType(prodCatalogCategoryTypeToBeAdded);

	}

	/**
	 * creates a new ProdCatalogCategoryType entry in the ofbiz database
	 * 
	 * @param prodCatalogCategoryTypeToBeAdded
	 *            the ProdCatalogCategoryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProdCatalogCategoryType(@RequestBody ProdCatalogCategoryType prodCatalogCategoryTypeToBeAdded) throws Exception {

		AddProdCatalogCategoryType command = new AddProdCatalogCategoryType(prodCatalogCategoryTypeToBeAdded);
		ProdCatalogCategoryType prodCatalogCategoryType = ((ProdCatalogCategoryTypeAdded) Scheduler.execute(command).data()).getAddedProdCatalogCategoryType();
		
		if (prodCatalogCategoryType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodCatalogCategoryType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdCatalogCategoryType could not be created.");
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
	public boolean updateProdCatalogCategoryType(HttpServletRequest request) throws Exception {

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

		ProdCatalogCategoryType prodCatalogCategoryTypeToBeUpdated = new ProdCatalogCategoryType();

		try {
			prodCatalogCategoryTypeToBeUpdated = ProdCatalogCategoryTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdCatalogCategoryType(prodCatalogCategoryTypeToBeUpdated, prodCatalogCategoryTypeToBeUpdated.getProdCatalogCategoryTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProdCatalogCategoryType with the specific Id
	 * 
	 * @param prodCatalogCategoryTypeToBeUpdated
	 *            the ProdCatalogCategoryType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{prodCatalogCategoryTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProdCatalogCategoryType(@RequestBody ProdCatalogCategoryType prodCatalogCategoryTypeToBeUpdated,
			@PathVariable String prodCatalogCategoryTypeId) throws Exception {

		prodCatalogCategoryTypeToBeUpdated.setProdCatalogCategoryTypeId(prodCatalogCategoryTypeId);

		UpdateProdCatalogCategoryType command = new UpdateProdCatalogCategoryType(prodCatalogCategoryTypeToBeUpdated);

		try {
			if(((ProdCatalogCategoryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{prodCatalogCategoryTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String prodCatalogCategoryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogCategoryTypeId", prodCatalogCategoryTypeId);
		try {

			Object foundProdCatalogCategoryType = findProdCatalogCategoryTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdCatalogCategoryType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{prodCatalogCategoryTypeId}")
	public ResponseEntity<Object> deleteProdCatalogCategoryTypeByIdUpdated(@PathVariable String prodCatalogCategoryTypeId) throws Exception {
		DeleteProdCatalogCategoryType command = new DeleteProdCatalogCategoryType(prodCatalogCategoryTypeId);

		try {
			if (((ProdCatalogCategoryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdCatalogCategoryType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/prodCatalogCategoryType/\" plus one of the following: "
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