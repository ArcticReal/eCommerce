package com.skytala.eCommerce.domain.order.relations.returnHeader;

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
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.AddReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.DeleteReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.UpdateReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderDeleted;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderFound;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.ReturnHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.query.FindReturnHeadersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnHeaders")
public class ReturnHeaderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnHeaderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnHeader
	 * @return a List with the ReturnHeaders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnHeader>> findReturnHeadersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnHeadersBy query = new FindReturnHeadersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnHeader> returnHeaders =((ReturnHeaderFound) Scheduler.execute(query).data()).getReturnHeaders();

		return ResponseEntity.ok().body(returnHeaders);

	}

	/**
	 * creates a new ReturnHeader entry in the ofbiz database
	 * 
	 * @param returnHeaderToBeAdded
	 *            the ReturnHeader thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnHeader> createReturnHeader(@RequestBody ReturnHeader returnHeaderToBeAdded) throws Exception {

		AddReturnHeader command = new AddReturnHeader(returnHeaderToBeAdded);
		ReturnHeader returnHeader = ((ReturnHeaderAdded) Scheduler.execute(command).data()).getAddedReturnHeader();
		
		if (returnHeader != null) 
			return successful(returnHeader);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnHeader with the specific Id
	 * 
	 * @param returnHeaderToBeUpdated
	 *            the ReturnHeader thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnHeader(@RequestBody ReturnHeader returnHeaderToBeUpdated,
			@PathVariable String returnId) throws Exception {

		returnHeaderToBeUpdated.setReturnId(returnId);

		UpdateReturnHeader command = new UpdateReturnHeader(returnHeaderToBeUpdated);

		try {
			if(((ReturnHeaderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnHeaderId}")
	public ResponseEntity<ReturnHeader> findById(@PathVariable String returnHeaderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnHeaderId", returnHeaderId);
		try {

			List<ReturnHeader> foundReturnHeader = findReturnHeadersBy(requestParams).getBody();
			if(foundReturnHeader.size()==1){				return successful(foundReturnHeader.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnHeaderId}")
	public ResponseEntity<String> deleteReturnHeaderByIdUpdated(@PathVariable String returnHeaderId) throws Exception {
		DeleteReturnHeader command = new DeleteReturnHeader(returnHeaderId);

		try {
			if (((ReturnHeaderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
