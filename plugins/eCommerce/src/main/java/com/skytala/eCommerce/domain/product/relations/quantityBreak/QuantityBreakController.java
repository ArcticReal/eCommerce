package com.skytala.eCommerce.domain.product.relations.quantityBreak;

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
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.AddQuantityBreak;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.DeleteQuantityBreak;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.UpdateQuantityBreak;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.QuantityBreakAdded;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.QuantityBreakDeleted;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.QuantityBreakFound;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.QuantityBreakUpdated;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper.QuantityBreakMapper;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.QuantityBreak;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.query.FindQuantityBreaksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/quantityBreaks")
public class QuantityBreakController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuantityBreakController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuantityBreak
	 * @return a List with the QuantityBreaks
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuantityBreak>> findQuantityBreaksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuantityBreaksBy query = new FindQuantityBreaksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuantityBreak> quantityBreaks =((QuantityBreakFound) Scheduler.execute(query).data()).getQuantityBreaks();

		return ResponseEntity.ok().body(quantityBreaks);

	}

	/**
	 * creates a new QuantityBreak entry in the ofbiz database
	 * 
	 * @param quantityBreakToBeAdded
	 *            the QuantityBreak thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuantityBreak> createQuantityBreak(@RequestBody QuantityBreak quantityBreakToBeAdded) throws Exception {

		AddQuantityBreak command = new AddQuantityBreak(quantityBreakToBeAdded);
		QuantityBreak quantityBreak = ((QuantityBreakAdded) Scheduler.execute(command).data()).getAddedQuantityBreak();
		
		if (quantityBreak != null) 
			return successful(quantityBreak);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuantityBreak with the specific Id
	 * 
	 * @param quantityBreakToBeUpdated
	 *            the QuantityBreak thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quantityBreakId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuantityBreak(@RequestBody QuantityBreak quantityBreakToBeUpdated,
			@PathVariable String quantityBreakId) throws Exception {

		quantityBreakToBeUpdated.setQuantityBreakId(quantityBreakId);

		UpdateQuantityBreak command = new UpdateQuantityBreak(quantityBreakToBeUpdated);

		try {
			if(((QuantityBreakUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quantityBreakId}")
	public ResponseEntity<QuantityBreak> findById(@PathVariable String quantityBreakId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quantityBreakId", quantityBreakId);
		try {

			List<QuantityBreak> foundQuantityBreak = findQuantityBreaksBy(requestParams).getBody();
			if(foundQuantityBreak.size()==1){				return successful(foundQuantityBreak.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quantityBreakId}")
	public ResponseEntity<String> deleteQuantityBreakByIdUpdated(@PathVariable String quantityBreakId) throws Exception {
		DeleteQuantityBreak command = new DeleteQuantityBreak(quantityBreakId);

		try {
			if (((QuantityBreakDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
