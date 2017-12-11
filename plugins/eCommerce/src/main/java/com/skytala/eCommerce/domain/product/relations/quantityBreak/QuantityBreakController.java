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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findQuantityBreaksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuantityBreaksBy query = new FindQuantityBreaksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuantityBreak> quantityBreaks =((QuantityBreakFound) Scheduler.execute(query).data()).getQuantityBreaks();

		if (quantityBreaks.size() == 1) {
			return ResponseEntity.ok().body(quantityBreaks.get(0));
		}

		return ResponseEntity.ok().body(quantityBreaks);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createQuantityBreak(HttpServletRequest request) throws Exception {

		QuantityBreak quantityBreakToBeAdded = new QuantityBreak();
		try {
			quantityBreakToBeAdded = QuantityBreakMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuantityBreak(quantityBreakToBeAdded);

	}

	/**
	 * creates a new QuantityBreak entry in the ofbiz database
	 * 
	 * @param quantityBreakToBeAdded
	 *            the QuantityBreak thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuantityBreak(@RequestBody QuantityBreak quantityBreakToBeAdded) throws Exception {

		AddQuantityBreak command = new AddQuantityBreak(quantityBreakToBeAdded);
		QuantityBreak quantityBreak = ((QuantityBreakAdded) Scheduler.execute(command).data()).getAddedQuantityBreak();
		
		if (quantityBreak != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quantityBreak);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuantityBreak could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateQuantityBreak(HttpServletRequest request) throws Exception {

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

		QuantityBreak quantityBreakToBeUpdated = new QuantityBreak();

		try {
			quantityBreakToBeUpdated = QuantityBreakMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuantityBreak(quantityBreakToBeUpdated, quantityBreakToBeUpdated.getQuantityBreakId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateQuantityBreak(@RequestBody QuantityBreak quantityBreakToBeUpdated,
			@PathVariable String quantityBreakId) throws Exception {

		quantityBreakToBeUpdated.setQuantityBreakId(quantityBreakId);

		UpdateQuantityBreak command = new UpdateQuantityBreak(quantityBreakToBeUpdated);

		try {
			if(((QuantityBreakUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{quantityBreakId}")
	public ResponseEntity<Object> findById(@PathVariable String quantityBreakId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quantityBreakId", quantityBreakId);
		try {

			Object foundQuantityBreak = findQuantityBreaksBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuantityBreak);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{quantityBreakId}")
	public ResponseEntity<Object> deleteQuantityBreakByIdUpdated(@PathVariable String quantityBreakId) throws Exception {
		DeleteQuantityBreak command = new DeleteQuantityBreak(quantityBreakId);

		try {
			if (((QuantityBreakDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuantityBreak could not be deleted");

	}

}
