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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/desiredFeatures")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDesiredFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDesiredFeaturesBy query = new FindDesiredFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DesiredFeature> desiredFeatures =((DesiredFeatureFound) Scheduler.execute(query).data()).getDesiredFeatures();

		if (desiredFeatures.size() == 1) {
			return ResponseEntity.ok().body(desiredFeatures.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createDesiredFeature(HttpServletRequest request) throws Exception {

		DesiredFeature desiredFeatureToBeAdded = new DesiredFeature();
		try {
			desiredFeatureToBeAdded = DesiredFeatureMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createDesiredFeature(@RequestBody DesiredFeature desiredFeatureToBeAdded) throws Exception {

		AddDesiredFeature command = new AddDesiredFeature(desiredFeatureToBeAdded);
		DesiredFeature desiredFeature = ((DesiredFeatureAdded) Scheduler.execute(command).data()).getAddedDesiredFeature();
		
		if (desiredFeature != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(desiredFeature);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DesiredFeature could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateDesiredFeature(HttpServletRequest request) throws Exception {

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

		DesiredFeature desiredFeatureToBeUpdated = new DesiredFeature();

		try {
			desiredFeatureToBeUpdated = DesiredFeatureMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDesiredFeature(desiredFeatureToBeUpdated, desiredFeatureToBeUpdated.getDesiredFeatureId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDesiredFeature(@RequestBody DesiredFeature desiredFeatureToBeUpdated,
			@PathVariable String desiredFeatureId) throws Exception {

		desiredFeatureToBeUpdated.setDesiredFeatureId(desiredFeatureId);

		UpdateDesiredFeature command = new UpdateDesiredFeature(desiredFeatureToBeUpdated);

		try {
			if(((DesiredFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{desiredFeatureId}")
	public ResponseEntity<Object> findById(@PathVariable String desiredFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("desiredFeatureId", desiredFeatureId);
		try {

			Object foundDesiredFeature = findDesiredFeaturesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDesiredFeature);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{desiredFeatureId}")
	public ResponseEntity<Object> deleteDesiredFeatureByIdUpdated(@PathVariable String desiredFeatureId) throws Exception {
		DeleteDesiredFeature command = new DeleteDesiredFeature(desiredFeatureId);

		try {
			if (((DesiredFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DesiredFeature could not be deleted");

	}

}
