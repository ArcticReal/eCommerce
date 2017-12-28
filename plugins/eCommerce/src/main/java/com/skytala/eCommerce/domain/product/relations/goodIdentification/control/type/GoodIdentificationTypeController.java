package com.skytala.eCommerce.domain.product.relations.goodIdentification.control.type;

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
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.type.AddGoodIdentificationType;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.type.DeleteGoodIdentificationType;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.command.type.UpdateGoodIdentificationType;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeAdded;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeFound;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.type.GoodIdentificationTypeMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.query.type.FindGoodIdentificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/goodIdentification/goodIdentificationTypes")
public class GoodIdentificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GoodIdentificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GoodIdentificationType
	 * @return a List with the GoodIdentificationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GoodIdentificationType>> findGoodIdentificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGoodIdentificationTypesBy query = new FindGoodIdentificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GoodIdentificationType> goodIdentificationTypes =((GoodIdentificationTypeFound) Scheduler.execute(query).data()).getGoodIdentificationTypes();

		return ResponseEntity.ok().body(goodIdentificationTypes);

	}

	/**
	 * creates a new GoodIdentificationType entry in the ofbiz database
	 * 
	 * @param goodIdentificationTypeToBeAdded
	 *            the GoodIdentificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GoodIdentificationType> createGoodIdentificationType(@RequestBody GoodIdentificationType goodIdentificationTypeToBeAdded) throws Exception {

		AddGoodIdentificationType command = new AddGoodIdentificationType(goodIdentificationTypeToBeAdded);
		GoodIdentificationType goodIdentificationType = ((GoodIdentificationTypeAdded) Scheduler.execute(command).data()).getAddedGoodIdentificationType();
		
		if (goodIdentificationType != null) 
			return successful(goodIdentificationType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GoodIdentificationType with the specific Id
	 * 
	 * @param goodIdentificationTypeToBeUpdated
	 *            the GoodIdentificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{goodIdentificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGoodIdentificationType(@RequestBody GoodIdentificationType goodIdentificationTypeToBeUpdated,
			@PathVariable String goodIdentificationTypeId) throws Exception {

		goodIdentificationTypeToBeUpdated.setGoodIdentificationTypeId(goodIdentificationTypeId);

		UpdateGoodIdentificationType command = new UpdateGoodIdentificationType(goodIdentificationTypeToBeUpdated);

		try {
			if(((GoodIdentificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{goodIdentificationTypeId}")
	public ResponseEntity<GoodIdentificationType> findById(@PathVariable String goodIdentificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("goodIdentificationTypeId", goodIdentificationTypeId);
		try {

			List<GoodIdentificationType> foundGoodIdentificationType = findGoodIdentificationTypesBy(requestParams).getBody();
			if(foundGoodIdentificationType.size()==1){				return successful(foundGoodIdentificationType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{goodIdentificationTypeId}")
	public ResponseEntity<String> deleteGoodIdentificationTypeByIdUpdated(@PathVariable String goodIdentificationTypeId) throws Exception {
		DeleteGoodIdentificationType command = new DeleteGoodIdentificationType(goodIdentificationTypeId);

		try {
			if (((GoodIdentificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
