package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.command.AddFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.command.DeleteFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.command.UpdateFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.mapper.FinAccountTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.query.FindFinAccountTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/finAccountTypeAttrs")
public class FinAccountTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTypeAttr
	 * @return a List with the FinAccountTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFinAccountTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypeAttrsBy query = new FindFinAccountTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTypeAttr> finAccountTypeAttrs =((FinAccountTypeAttrFound) Scheduler.execute(query).data()).getFinAccountTypeAttrs();

		if (finAccountTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(finAccountTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(finAccountTypeAttrs);

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
	public ResponseEntity<Object> createFinAccountTypeAttr(HttpServletRequest request) throws Exception {

		FinAccountTypeAttr finAccountTypeAttrToBeAdded = new FinAccountTypeAttr();
		try {
			finAccountTypeAttrToBeAdded = FinAccountTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountTypeAttr(finAccountTypeAttrToBeAdded);

	}

	/**
	 * creates a new FinAccountTypeAttr entry in the ofbiz database
	 * 
	 * @param finAccountTypeAttrToBeAdded
	 *            the FinAccountTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountTypeAttr(@RequestBody FinAccountTypeAttr finAccountTypeAttrToBeAdded) throws Exception {

		AddFinAccountTypeAttr command = new AddFinAccountTypeAttr(finAccountTypeAttrToBeAdded);
		FinAccountTypeAttr finAccountTypeAttr = ((FinAccountTypeAttrAdded) Scheduler.execute(command).data()).getAddedFinAccountTypeAttr();
		
		if (finAccountTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountTypeAttr could not be created.");
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
	public boolean updateFinAccountTypeAttr(HttpServletRequest request) throws Exception {

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

		FinAccountTypeAttr finAccountTypeAttrToBeUpdated = new FinAccountTypeAttr();

		try {
			finAccountTypeAttrToBeUpdated = FinAccountTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountTypeAttr(finAccountTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FinAccountTypeAttr with the specific Id
	 * 
	 * @param finAccountTypeAttrToBeUpdated
	 *            the FinAccountTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFinAccountTypeAttr(@RequestBody FinAccountTypeAttr finAccountTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountTypeAttrToBeUpdated.setnull(null);

		UpdateFinAccountTypeAttr command = new UpdateFinAccountTypeAttr(finAccountTypeAttrToBeUpdated);

		try {
			if(((FinAccountTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{finAccountTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeAttrId", finAccountTypeAttrId);
		try {

			Object foundFinAccountTypeAttr = findFinAccountTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{finAccountTypeAttrId}")
	public ResponseEntity<Object> deleteFinAccountTypeAttrByIdUpdated(@PathVariable String finAccountTypeAttrId) throws Exception {
		DeleteFinAccountTypeAttr command = new DeleteFinAccountTypeAttr(finAccountTypeAttrId);

		try {
			if (((FinAccountTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/finAccountTypeAttr/\" plus one of the following: "
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
