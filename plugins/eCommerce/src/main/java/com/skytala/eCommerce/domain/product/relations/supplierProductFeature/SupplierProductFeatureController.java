package com.skytala.eCommerce.domain.product.relations.supplierProductFeature;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.command.AddSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.command.DeleteSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.command.UpdateSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureDeleted;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureFound;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureUpdated;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.mapper.SupplierProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model.SupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.query.FindSupplierProductFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/supplierProductFeatures")
public class SupplierProductFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierProductFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierProductFeature
	 * @return a List with the SupplierProductFeatures
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSupplierProductFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierProductFeaturesBy query = new FindSupplierProductFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierProductFeature> supplierProductFeatures =((SupplierProductFeatureFound) Scheduler.execute(query).data()).getSupplierProductFeatures();

		if (supplierProductFeatures.size() == 1) {
			return ResponseEntity.ok().body(supplierProductFeatures.get(0));
		}

		return ResponseEntity.ok().body(supplierProductFeatures);

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
	public ResponseEntity<Object> createSupplierProductFeature(HttpServletRequest request) throws Exception {

		SupplierProductFeature supplierProductFeatureToBeAdded = new SupplierProductFeature();
		try {
			supplierProductFeatureToBeAdded = SupplierProductFeatureMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSupplierProductFeature(supplierProductFeatureToBeAdded);

	}

	/**
	 * creates a new SupplierProductFeature entry in the ofbiz database
	 * 
	 * @param supplierProductFeatureToBeAdded
	 *            the SupplierProductFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSupplierProductFeature(@RequestBody SupplierProductFeature supplierProductFeatureToBeAdded) throws Exception {

		AddSupplierProductFeature command = new AddSupplierProductFeature(supplierProductFeatureToBeAdded);
		SupplierProductFeature supplierProductFeature = ((SupplierProductFeatureAdded) Scheduler.execute(command).data()).getAddedSupplierProductFeature();
		
		if (supplierProductFeature != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(supplierProductFeature);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SupplierProductFeature could not be created.");
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
	public boolean updateSupplierProductFeature(HttpServletRequest request) throws Exception {

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

		SupplierProductFeature supplierProductFeatureToBeUpdated = new SupplierProductFeature();

		try {
			supplierProductFeatureToBeUpdated = SupplierProductFeatureMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSupplierProductFeature(supplierProductFeatureToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SupplierProductFeature with the specific Id
	 * 
	 * @param supplierProductFeatureToBeUpdated
	 *            the SupplierProductFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSupplierProductFeature(@RequestBody SupplierProductFeature supplierProductFeatureToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		supplierProductFeatureToBeUpdated.setnull(null);

		UpdateSupplierProductFeature command = new UpdateSupplierProductFeature(supplierProductFeatureToBeUpdated);

		try {
			if(((SupplierProductFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{supplierProductFeatureId}")
	public ResponseEntity<Object> findById(@PathVariable String supplierProductFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierProductFeatureId", supplierProductFeatureId);
		try {

			Object foundSupplierProductFeature = findSupplierProductFeaturesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSupplierProductFeature);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{supplierProductFeatureId}")
	public ResponseEntity<Object> deleteSupplierProductFeatureByIdUpdated(@PathVariable String supplierProductFeatureId) throws Exception {
		DeleteSupplierProductFeature command = new DeleteSupplierProductFeature(supplierProductFeatureId);

		try {
			if (((SupplierProductFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SupplierProductFeature could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/supplierProductFeature/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
