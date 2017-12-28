package com.skytala.eCommerce.domain.content.relations.dataResource.control.other;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.other.AddOtherDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.other.DeleteOtherDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.other.UpdateOtherDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.other.OtherDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.other.OtherDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.other.FindOtherDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/otherDataResources")
public class OtherDataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OtherDataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OtherDataResource
	 * @return a List with the OtherDataResources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OtherDataResource>> findOtherDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOtherDataResourcesBy query = new FindOtherDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OtherDataResource> otherDataResources =((OtherDataResourceFound) Scheduler.execute(query).data()).getOtherDataResources();

		return ResponseEntity.ok().body(otherDataResources);

	}

	/**
	 * creates a new OtherDataResource entry in the ofbiz database
	 * 
	 * @param otherDataResourceToBeAdded
	 *            the OtherDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OtherDataResource> createOtherDataResource(@RequestBody OtherDataResource otherDataResourceToBeAdded) throws Exception {

		AddOtherDataResource command = new AddOtherDataResource(otherDataResourceToBeAdded);
		OtherDataResource otherDataResource = ((OtherDataResourceAdded) Scheduler.execute(command).data()).getAddedOtherDataResource();
		
		if (otherDataResource != null) 
			return successful(otherDataResource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OtherDataResource with the specific Id
	 * 
	 * @param otherDataResourceToBeUpdated
	 *            the OtherDataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOtherDataResource(@RequestBody OtherDataResource otherDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		otherDataResourceToBeUpdated.setnull(null);

		UpdateOtherDataResource command = new UpdateOtherDataResource(otherDataResourceToBeUpdated);

		try {
			if(((OtherDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{otherDataResourceId}")
	public ResponseEntity<OtherDataResource> findById(@PathVariable String otherDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("otherDataResourceId", otherDataResourceId);
		try {

			List<OtherDataResource> foundOtherDataResource = findOtherDataResourcesBy(requestParams).getBody();
			if(foundOtherDataResource.size()==1){				return successful(foundOtherDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{otherDataResourceId}")
	public ResponseEntity<String> deleteOtherDataResourceByIdUpdated(@PathVariable String otherDataResourceId) throws Exception {
		DeleteOtherDataResource command = new DeleteOtherDataResource(otherDataResourceId);

		try {
			if (((OtherDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
