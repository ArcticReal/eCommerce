package com.skytala.eCommerce.domain.order.relations.custRequest.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.AddCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.DeleteCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.UpdateCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.typeAttr.CustRequestTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.typeAttr.FindCustRequestTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/custRequestTypeAttrs")
public class CustRequestTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestTypeAttr
	 * @return a List with the CustRequestTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestTypeAttrsBy query = new FindCustRequestTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestTypeAttr> custRequestTypeAttrs =((CustRequestTypeAttrFound) Scheduler.execute(query).data()).getCustRequestTypeAttrs();

		if (custRequestTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(custRequestTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(custRequestTypeAttrs);

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
	public ResponseEntity<Object> createCustRequestTypeAttr(HttpServletRequest request) throws Exception {

		CustRequestTypeAttr custRequestTypeAttrToBeAdded = new CustRequestTypeAttr();
		try {
			custRequestTypeAttrToBeAdded = CustRequestTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestTypeAttr(custRequestTypeAttrToBeAdded);

	}

	/**
	 * creates a new CustRequestTypeAttr entry in the ofbiz database
	 * 
	 * @param custRequestTypeAttrToBeAdded
	 *            the CustRequestTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestTypeAttr(@RequestBody CustRequestTypeAttr custRequestTypeAttrToBeAdded) throws Exception {

		AddCustRequestTypeAttr command = new AddCustRequestTypeAttr(custRequestTypeAttrToBeAdded);
		CustRequestTypeAttr custRequestTypeAttr = ((CustRequestTypeAttrAdded) Scheduler.execute(command).data()).getAddedCustRequestTypeAttr();
		
		if (custRequestTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestTypeAttr could not be created.");
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
	public boolean updateCustRequestTypeAttr(HttpServletRequest request) throws Exception {

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

		CustRequestTypeAttr custRequestTypeAttrToBeUpdated = new CustRequestTypeAttr();

		try {
			custRequestTypeAttrToBeUpdated = CustRequestTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestTypeAttr(custRequestTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestTypeAttr with the specific Id
	 * 
	 * @param custRequestTypeAttrToBeUpdated
	 *            the CustRequestTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestTypeAttr(@RequestBody CustRequestTypeAttr custRequestTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestTypeAttrToBeUpdated.setnull(null);

		UpdateCustRequestTypeAttr command = new UpdateCustRequestTypeAttr(custRequestTypeAttrToBeUpdated);

		try {
			if(((CustRequestTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestTypeAttrId", custRequestTypeAttrId);
		try {

			Object foundCustRequestTypeAttr = findCustRequestTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestTypeAttrId}")
	public ResponseEntity<Object> deleteCustRequestTypeAttrByIdUpdated(@PathVariable String custRequestTypeAttrId) throws Exception {
		DeleteCustRequestTypeAttr command = new DeleteCustRequestTypeAttr(custRequestTypeAttrId);

		try {
			if (((CustRequestTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/custRequestTypeAttr/\" plus one of the following: "
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
