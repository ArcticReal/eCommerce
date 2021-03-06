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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<GoodIdentification>> findGoodIdentificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGoodIdentificationsBy query = new FindGoodIdentificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GoodIdentification> goodIdentifications =((GoodIdentificationFound) Scheduler.execute(query).data()).getGoodIdentifications();

		return ResponseEntity.ok().body(goodIdentifications);

	}

	/**
	 * creates a new GoodIdentification entry in the ofbiz database
	 * 
	 * @param goodIdentificationToBeAdded
	 *            the GoodIdentification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GoodIdentification> createGoodIdentification(@RequestBody GoodIdentification goodIdentificationToBeAdded) throws Exception {

		AddGoodIdentification command = new AddGoodIdentification(goodIdentificationToBeAdded);
		GoodIdentification goodIdentification = ((GoodIdentificationAdded) Scheduler.execute(command).data()).getAddedGoodIdentification();
		
		if (goodIdentification != null) 
			return successful(goodIdentification);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGoodIdentification(@RequestBody GoodIdentification goodIdentificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		goodIdentificationToBeUpdated.setnull(null);

		UpdateGoodIdentification command = new UpdateGoodIdentification(goodIdentificationToBeUpdated);

		try {
			if(((GoodIdentificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{goodIdentificationId}")
	public ResponseEntity<GoodIdentification> findById(@PathVariable String goodIdentificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("goodIdentificationId", goodIdentificationId);
		try {

			List<GoodIdentification> foundGoodIdentification = findGoodIdentificationsBy(requestParams).getBody();
			if(foundGoodIdentification.size()==1){				return successful(foundGoodIdentification.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{goodIdentificationId}")
	public ResponseEntity<String> deleteGoodIdentificationByIdUpdated(@PathVariable String goodIdentificationId) throws Exception {
		DeleteGoodIdentification command = new DeleteGoodIdentification(goodIdentificationId);

		try {
			if (((GoodIdentificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
