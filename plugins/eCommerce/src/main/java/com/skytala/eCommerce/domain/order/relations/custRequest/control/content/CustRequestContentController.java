package com.skytala.eCommerce.domain.order.relations.custRequest.control.content;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.content.AddCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.content.DeleteCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.content.UpdateCustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.content.CustRequestContentAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.content.CustRequestContentDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.content.CustRequestContentFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.content.CustRequestContentUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.content.CustRequestContentMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.content.FindCustRequestContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestContents")
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
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestContent>> findCustRequestContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestContentsBy query = new FindCustRequestContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestContent> custRequestContents =((CustRequestContentFound) Scheduler.execute(query).data()).getCustRequestContents();

		return ResponseEntity.ok().body(custRequestContents);

	}

	/**
	 * creates a new CustRequestContent entry in the ofbiz database
	 * 
	 * @param custRequestContentToBeAdded
	 *            the CustRequestContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestContent> createCustRequestContent(@RequestBody CustRequestContent custRequestContentToBeAdded) throws Exception {

		AddCustRequestContent command = new AddCustRequestContent(custRequestContentToBeAdded);
		CustRequestContent custRequestContent = ((CustRequestContentAdded) Scheduler.execute(command).data()).getAddedCustRequestContent();
		
		if (custRequestContent != null) 
			return successful(custRequestContent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestContent(@RequestBody CustRequestContent custRequestContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestContentToBeUpdated.setnull(null);

		UpdateCustRequestContent command = new UpdateCustRequestContent(custRequestContentToBeUpdated);

		try {
			if(((CustRequestContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestContentId}")
	public ResponseEntity<CustRequestContent> findById(@PathVariable String custRequestContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestContentId", custRequestContentId);
		try {

			List<CustRequestContent> foundCustRequestContent = findCustRequestContentsBy(requestParams).getBody();
			if(foundCustRequestContent.size()==1){				return successful(foundCustRequestContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestContentId}")
	public ResponseEntity<String> deleteCustRequestContentByIdUpdated(@PathVariable String custRequestContentId) throws Exception {
		DeleteCustRequestContent command = new DeleteCustRequestContent(custRequestContentId);

		try {
			if (((CustRequestContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
