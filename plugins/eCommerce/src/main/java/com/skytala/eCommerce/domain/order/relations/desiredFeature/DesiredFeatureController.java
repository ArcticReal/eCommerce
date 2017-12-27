package com.skytala.eCommerce.domain.order.relations.desiredFeature;

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
import com.skytala.eCommerce.domain.order.relations.desiredFeature.command.AddDesiredFeature;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.command.DeleteDesiredFeature;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.command.UpdateDesiredFeature;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.event.DesiredFeatureAdded;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.event.DesiredFeatureDeleted;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.event.DesiredFeatureFound;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.event.DesiredFeatureUpdated;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.mapper.DesiredFeatureMapper;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.model.DesiredFeature;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.query.FindDesiredFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/desiredFeatures")
public class DesiredFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DesiredFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DesiredFeature
	 * @return a List with the DesiredFeatures
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DesiredFeature>> findDesiredFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDesiredFeaturesBy query = new FindDesiredFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DesiredFeature> desiredFeatures =((DesiredFeatureFound) Scheduler.execute(query).data()).getDesiredFeatures();

		return ResponseEntity.ok().body(desiredFeatures);

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
	public ResponseEntity<DesiredFeature> createDesiredFeature(HttpServletRequest request) throws Exception {

		DesiredFeature desiredFeatureToBeAdded = new DesiredFeature();
		try {
			desiredFeatureToBeAdded = DesiredFeatureMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createDesiredFeature(desiredFeatureToBeAdded);

	}

	/**
	 * creates a new DesiredFeature entry in the ofbiz database
	 * 
	 * @param desiredFeatureToBeAdded
	 *            the DesiredFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DesiredFeature> createDesiredFeature(@RequestBody DesiredFeature desiredFeatureToBeAdded) throws Exception {

		AddDesiredFeature command = new AddDesiredFeature(desiredFeatureToBeAdded);
		DesiredFeature desiredFeature = ((DesiredFeatureAdded) Scheduler.execute(command).data()).getAddedDesiredFeature();
		
		if (desiredFeature != null) 
			return successful(desiredFeature);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DesiredFeature with the specific Id
	 * 
	 * @param desiredFeatureToBeUpdated
	 *            the DesiredFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{desiredFeatureId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDesiredFeature(@RequestBody DesiredFeature desiredFeatureToBeUpdated,
			@PathVariable String desiredFeatureId) throws Exception {

		desiredFeatureToBeUpdated.setDesiredFeatureId(desiredFeatureId);

		UpdateDesiredFeature command = new UpdateDesiredFeature(desiredFeatureToBeUpdated);

		try {
			if(((DesiredFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{desiredFeatureId}")
	public ResponseEntity<DesiredFeature> findById(@PathVariable String desiredFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("desiredFeatureId", desiredFeatureId);
		try {

			List<DesiredFeature> foundDesiredFeature = findDesiredFeaturesBy(requestParams).getBody();
			if(foundDesiredFeature.size()==1){				return successful(foundDesiredFeature.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{desiredFeatureId}")
	public ResponseEntity<String> deleteDesiredFeatureByIdUpdated(@PathVariable String desiredFeatureId) throws Exception {
		DeleteDesiredFeature command = new DeleteDesiredFeature(desiredFeatureId);

		try {
			if (((DesiredFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
