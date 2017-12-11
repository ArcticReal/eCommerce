package com.skytala.eCommerce.domain.party.relations.termType.control.attr;

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
import com.skytala.eCommerce.domain.party.relations.termType.command.attr.AddTermTypeAttr;
import com.skytala.eCommerce.domain.party.relations.termType.command.attr.DeleteTermTypeAttr;
import com.skytala.eCommerce.domain.party.relations.termType.command.attr.UpdateTermTypeAttr;
import com.skytala.eCommerce.domain.party.relations.termType.event.attr.TermTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.termType.event.attr.TermTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.termType.event.attr.TermTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.termType.event.attr.TermTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.termType.mapper.attr.TermTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.termType.model.attr.TermTypeAttr;
import com.skytala.eCommerce.domain.party.relations.termType.query.attr.FindTermTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/termType/termTypeAttrs")
public class TermTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TermTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TermTypeAttr
	 * @return a List with the TermTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTermTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTermTypeAttrsBy query = new FindTermTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TermTypeAttr> termTypeAttrs =((TermTypeAttrFound) Scheduler.execute(query).data()).getTermTypeAttrs();

		if (termTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(termTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(termTypeAttrs);

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
	public ResponseEntity<Object> createTermTypeAttr(HttpServletRequest request) throws Exception {

		TermTypeAttr termTypeAttrToBeAdded = new TermTypeAttr();
		try {
			termTypeAttrToBeAdded = TermTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTermTypeAttr(termTypeAttrToBeAdded);

	}

	/**
	 * creates a new TermTypeAttr entry in the ofbiz database
	 * 
	 * @param termTypeAttrToBeAdded
	 *            the TermTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTermTypeAttr(@RequestBody TermTypeAttr termTypeAttrToBeAdded) throws Exception {

		AddTermTypeAttr command = new AddTermTypeAttr(termTypeAttrToBeAdded);
		TermTypeAttr termTypeAttr = ((TermTypeAttrAdded) Scheduler.execute(command).data()).getAddedTermTypeAttr();
		
		if (termTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(termTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TermTypeAttr could not be created.");
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
	public boolean updateTermTypeAttr(HttpServletRequest request) throws Exception {

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

		TermTypeAttr termTypeAttrToBeUpdated = new TermTypeAttr();

		try {
			termTypeAttrToBeUpdated = TermTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTermTypeAttr(termTypeAttrToBeUpdated, termTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TermTypeAttr with the specific Id
	 * 
	 * @param termTypeAttrToBeUpdated
	 *            the TermTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTermTypeAttr(@RequestBody TermTypeAttr termTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		termTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateTermTypeAttr command = new UpdateTermTypeAttr(termTypeAttrToBeUpdated);

		try {
			if(((TermTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{termTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String termTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("termTypeAttrId", termTypeAttrId);
		try {

			Object foundTermTypeAttr = findTermTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTermTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{termTypeAttrId}")
	public ResponseEntity<Object> deleteTermTypeAttrByIdUpdated(@PathVariable String termTypeAttrId) throws Exception {
		DeleteTermTypeAttr command = new DeleteTermTypeAttr(termTypeAttrId);

		try {
			if (((TermTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TermTypeAttr could not be deleted");

	}

}
