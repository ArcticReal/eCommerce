package com.skytala.eCommerce.domain.product.relations.goodIdentification;

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
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.AddGoodIdentification;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.DeleteGoodIdentification;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.UpdateGoodIdentification;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationAdded;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationDeleted;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationFound;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationUpdated;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.GoodIdentificationMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.query.FindGoodIdentificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/goodIdentifications")
public class GoodIdentificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GoodIdentificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GoodIdentification
	 * @return a List with the GoodIdentifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findGoodIdentificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGoodIdentificationsBy query = new FindGoodIdentificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GoodIdentification> goodIdentifications =((GoodIdentificationFound) Scheduler.execute(query).data()).getGoodIdentifications();

		if (goodIdentifications.size() == 1) {
			return ResponseEntity.ok().body(goodIdentifications.get(0));
		}

		return ResponseEntity.ok().body(goodIdentifications);

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
	public ResponseEntity<Object> createGoodIdentification(HttpServletRequest request) throws Exception {

		GoodIdentification goodIdentificationToBeAdded = new GoodIdentification();
		try {
			goodIdentificationToBeAdded = GoodIdentificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGoodIdentification(goodIdentificationToBeAdded);

	}

	/**
	 * creates a new GoodIdentification entry in the ofbiz database
	 * 
	 * @param goodIdentificationToBeAdded
	 *            the GoodIdentification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGoodIdentification(@RequestBody GoodIdentification goodIdentificationToBeAdded) throws Exception {

		AddGoodIdentification command = new AddGoodIdentification(goodIdentificationToBeAdded);
		GoodIdentification goodIdentification = ((GoodIdentificationAdded) Scheduler.execute(command).data()).getAddedGoodIdentification();
		
		if (goodIdentification != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(goodIdentification);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GoodIdentification could not be created.");
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
	public boolean updateGoodIdentification(HttpServletRequest request) throws Exception {

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

		GoodIdentification goodIdentificationToBeUpdated = new GoodIdentification();

		try {
			goodIdentificationToBeUpdated = GoodIdentificationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGoodIdentification(goodIdentificationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GoodIdentification with the specific Id
	 * 
	 * @param goodIdentificationToBeUpdated
	 *            the GoodIdentification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGoodIdentification(@RequestBody GoodIdentification goodIdentificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		goodIdentificationToBeUpdated.setnull(null);

		UpdateGoodIdentification command = new UpdateGoodIdentification(goodIdentificationToBeUpdated);

		try {
			if(((GoodIdentificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{goodIdentificationId}")
	public ResponseEntity<Object> findById(@PathVariable String goodIdentificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("goodIdentificationId", goodIdentificationId);
		try {

			Object foundGoodIdentification = findGoodIdentificationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGoodIdentification);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{goodIdentificationId}")
	public ResponseEntity<Object> deleteGoodIdentificationByIdUpdated(@PathVariable String goodIdentificationId) throws Exception {
		DeleteGoodIdentification command = new DeleteGoodIdentification(goodIdentificationId);

		try {
			if (((GoodIdentificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GoodIdentification could not be deleted");

	}

}
