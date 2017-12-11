package com.skytala.eCommerce.domain.order.relations.custRequest.control.resolution;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.AddCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.DeleteCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.UpdateCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.resolution.CustRequestResolutionMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.resolution.FindCustRequestResolutionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/custRequest/custRequestResolutions")
public class CustRequestResolutionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestResolutionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestResolution
	 * @return a List with the CustRequestResolutions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCustRequestResolutionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestResolutionsBy query = new FindCustRequestResolutionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestResolution> custRequestResolutions =((CustRequestResolutionFound) Scheduler.execute(query).data()).getCustRequestResolutions();

		if (custRequestResolutions.size() == 1) {
			return ResponseEntity.ok().body(custRequestResolutions.get(0));
		}

		return ResponseEntity.ok().body(custRequestResolutions);

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
	public ResponseEntity<Object> createCustRequestResolution(HttpServletRequest request) throws Exception {

		CustRequestResolution custRequestResolutionToBeAdded = new CustRequestResolution();
		try {
			custRequestResolutionToBeAdded = CustRequestResolutionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestResolution(custRequestResolutionToBeAdded);

	}

	/**
	 * creates a new CustRequestResolution entry in the ofbiz database
	 * 
	 * @param custRequestResolutionToBeAdded
	 *            the CustRequestResolution thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestResolution(@RequestBody CustRequestResolution custRequestResolutionToBeAdded) throws Exception {

		AddCustRequestResolution command = new AddCustRequestResolution(custRequestResolutionToBeAdded);
		CustRequestResolution custRequestResolution = ((CustRequestResolutionAdded) Scheduler.execute(command).data()).getAddedCustRequestResolution();
		
		if (custRequestResolution != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestResolution);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestResolution could not be created.");
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
	public boolean updateCustRequestResolution(HttpServletRequest request) throws Exception {

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

		CustRequestResolution custRequestResolutionToBeUpdated = new CustRequestResolution();

		try {
			custRequestResolutionToBeUpdated = CustRequestResolutionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestResolution(custRequestResolutionToBeUpdated, custRequestResolutionToBeUpdated.getCustRequestResolutionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestResolution with the specific Id
	 * 
	 * @param custRequestResolutionToBeUpdated
	 *            the CustRequestResolution thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestResolutionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestResolution(@RequestBody CustRequestResolution custRequestResolutionToBeUpdated,
			@PathVariable String custRequestResolutionId) throws Exception {

		custRequestResolutionToBeUpdated.setCustRequestResolutionId(custRequestResolutionId);

		UpdateCustRequestResolution command = new UpdateCustRequestResolution(custRequestResolutionToBeUpdated);

		try {
			if(((CustRequestResolutionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{custRequestResolutionId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestResolutionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestResolutionId", custRequestResolutionId);
		try {

			Object foundCustRequestResolution = findCustRequestResolutionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestResolution);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{custRequestResolutionId}")
	public ResponseEntity<Object> deleteCustRequestResolutionByIdUpdated(@PathVariable String custRequestResolutionId) throws Exception {
		DeleteCustRequestResolution command = new DeleteCustRequestResolution(custRequestResolutionId);

		try {
			if (((CustRequestResolutionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestResolution could not be deleted");

	}

}
