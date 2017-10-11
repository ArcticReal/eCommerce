package com.skytala.eCommerce.domain.product.relations.prodConfItemContentType;

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
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.command.AddProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.command.DeleteProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.command.UpdateProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.mapper.ProdConfItemContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.model.ProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.query.FindProdConfItemContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/prodConfItemContentTypes")
public class ProdConfItemContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdConfItemContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdConfItemContentType
	 * @return a List with the ProdConfItemContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProdConfItemContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdConfItemContentTypesBy query = new FindProdConfItemContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdConfItemContentType> prodConfItemContentTypes =((ProdConfItemContentTypeFound) Scheduler.execute(query).data()).getProdConfItemContentTypes();

		if (prodConfItemContentTypes.size() == 1) {
			return ResponseEntity.ok().body(prodConfItemContentTypes.get(0));
		}

		return ResponseEntity.ok().body(prodConfItemContentTypes);

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
	public ResponseEntity<Object> createProdConfItemContentType(HttpServletRequest request) throws Exception {

		ProdConfItemContentType prodConfItemContentTypeToBeAdded = new ProdConfItemContentType();
		try {
			prodConfItemContentTypeToBeAdded = ProdConfItemContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProdConfItemContentType(prodConfItemContentTypeToBeAdded);

	}

	/**
	 * creates a new ProdConfItemContentType entry in the ofbiz database
	 * 
	 * @param prodConfItemContentTypeToBeAdded
	 *            the ProdConfItemContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProdConfItemContentType(@RequestBody ProdConfItemContentType prodConfItemContentTypeToBeAdded) throws Exception {

		AddProdConfItemContentType command = new AddProdConfItemContentType(prodConfItemContentTypeToBeAdded);
		ProdConfItemContentType prodConfItemContentType = ((ProdConfItemContentTypeAdded) Scheduler.execute(command).data()).getAddedProdConfItemContentType();
		
		if (prodConfItemContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodConfItemContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdConfItemContentType could not be created.");
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
	public boolean updateProdConfItemContentType(HttpServletRequest request) throws Exception {

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

		ProdConfItemContentType prodConfItemContentTypeToBeUpdated = new ProdConfItemContentType();

		try {
			prodConfItemContentTypeToBeUpdated = ProdConfItemContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdConfItemContentType(prodConfItemContentTypeToBeUpdated, prodConfItemContentTypeToBeUpdated.getConfItemContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProdConfItemContentType with the specific Id
	 * 
	 * @param prodConfItemContentTypeToBeUpdated
	 *            the ProdConfItemContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{confItemContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProdConfItemContentType(@RequestBody ProdConfItemContentType prodConfItemContentTypeToBeUpdated,
			@PathVariable String confItemContentTypeId) throws Exception {

		prodConfItemContentTypeToBeUpdated.setConfItemContentTypeId(confItemContentTypeId);

		UpdateProdConfItemContentType command = new UpdateProdConfItemContentType(prodConfItemContentTypeToBeUpdated);

		try {
			if(((ProdConfItemContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{prodConfItemContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String prodConfItemContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodConfItemContentTypeId", prodConfItemContentTypeId);
		try {

			Object foundProdConfItemContentType = findProdConfItemContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdConfItemContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{prodConfItemContentTypeId}")
	public ResponseEntity<Object> deleteProdConfItemContentTypeByIdUpdated(@PathVariable String prodConfItemContentTypeId) throws Exception {
		DeleteProdConfItemContentType command = new DeleteProdConfItemContentType(prodConfItemContentTypeId);

		try {
			if (((ProdConfItemContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdConfItemContentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/prodConfItemContentType/\" plus one of the following: "
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