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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<TermTypeAttr>> findTermTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTermTypeAttrsBy query = new FindTermTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TermTypeAttr> termTypeAttrs =((TermTypeAttrFound) Scheduler.execute(query).data()).getTermTypeAttrs();

		return ResponseEntity.ok().body(termTypeAttrs);

	}

	/**
	 * creates a new TermTypeAttr entry in the ofbiz database
	 * 
	 * @param termTypeAttrToBeAdded
	 *            the TermTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TermTypeAttr> createTermTypeAttr(@RequestBody TermTypeAttr termTypeAttrToBeAdded) throws Exception {

		AddTermTypeAttr command = new AddTermTypeAttr(termTypeAttrToBeAdded);
		TermTypeAttr termTypeAttr = ((TermTypeAttrAdded) Scheduler.execute(command).data()).getAddedTermTypeAttr();
		
		if (termTypeAttr != null) 
			return successful(termTypeAttr);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTermTypeAttr(@RequestBody TermTypeAttr termTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		termTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateTermTypeAttr command = new UpdateTermTypeAttr(termTypeAttrToBeUpdated);

		try {
			if(((TermTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{termTypeAttrId}")
	public ResponseEntity<TermTypeAttr> findById(@PathVariable String termTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("termTypeAttrId", termTypeAttrId);
		try {

			List<TermTypeAttr> foundTermTypeAttr = findTermTypeAttrsBy(requestParams).getBody();
			if(foundTermTypeAttr.size()==1){				return successful(foundTermTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{termTypeAttrId}")
	public ResponseEntity<String> deleteTermTypeAttrByIdUpdated(@PathVariable String termTypeAttrId) throws Exception {
		DeleteTermTypeAttr command = new DeleteTermTypeAttr(termTypeAttrId);

		try {
			if (((TermTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
