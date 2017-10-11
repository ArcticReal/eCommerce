package com.skytala.eCommerce.domain.order.relations.custRequestContent;

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
import com.skytala.eCommerce.domain.order.relations.custRequestContent.command.AddCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.command.DeleteCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.command.UpdateCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.event.CustRequestContentAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.event.CustRequestContentDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.event.CustRequestContentFound;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.event.CustRequestContentUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.mapper.CustRequestContentMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.model.CustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.query.FindCustRequestContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/custRequestContents")
public class CustRequestContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestContent
	 * @return a List with the CustRequestContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestContentsBy query = new FindCustRequestContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestContent> custRequestContents =((CustRequestContentFound) Scheduler.execute(query).data()).getCustRequestContents();

		if (custRequestContents.size() == 1) {
			return ResponseEntity.ok().body(custRequestContents.get(0));
		}

		return ResponseEntity.ok().body(custRequestContents);

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
	public ResponseEntity<Object> createCustRequestContent(HttpServletRequest request) throws Exception {

		CustRequestContent custRequestContentToBeAdded = new CustRequestContent();
		try {
			custRequestContentToBeAdded = CustRequestContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestContent(custRequestContentToBeAdded);

	}

	/**
	 * creates a new CustRequestContent entry in the ofbiz database
	 * 
	 * @param custRequestContentToBeAdded
	 *            the CustRequestContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestContent(@RequestBody CustRequestContent custRequestContentToBeAdded) throws Exception {

		AddCustRequestContent command = new AddCustRequestContent(custRequestContentToBeAdded);
		CustRequestContent custRequestContent = ((CustRequestContentAdded) Scheduler.execute(command).data()).getAddedCustRequestContent();
		
		if (custRequestContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestContent could not be created.");
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
	public boolean updateCustRequestContent(HttpServletRequest request) throws Exception {

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

		CustRequestContent custRequestContentToBeUpdated = new CustRequestContent();

		try {
			custRequestContentToBeUpdated = CustRequestContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestContent(custRequestContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestContent with the specific Id
	 * 
	 * @param custRequestContentToBeUpdated
	 *            the CustRequestContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestContent(@RequestBody CustRequestContent custRequestContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestContentToBeUpdated.setnull(null);

		UpdateCustRequestContent command = new UpdateCustRequestContent(custRequestContentToBeUpdated);

		try {
			if(((CustRequestContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestContentId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestContentId", custRequestContentId);
		try {

			Object foundCustRequestContent = findCustRequestContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestContentId}")
	public ResponseEntity<Object> deleteCustRequestContentByIdUpdated(@PathVariable String custRequestContentId) throws Exception {
		DeleteCustRequestContent command = new DeleteCustRequestContent(custRequestContentId);

		try {
			if (((CustRequestContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/custRequestContent/\" plus one of the following: "
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
