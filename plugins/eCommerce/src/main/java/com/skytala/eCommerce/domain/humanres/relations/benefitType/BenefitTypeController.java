package com.skytala.eCommerce.domain.humanres.relations.benefitType;

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
import com.skytala.eCommerce.domain.humanres.relations.benefitType.command.AddBenefitType;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.command.DeleteBenefitType;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.command.UpdateBenefitType;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.event.BenefitTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.event.BenefitTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.event.BenefitTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.event.BenefitTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.mapper.BenefitTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.model.BenefitType;
import com.skytala.eCommerce.domain.humanres.relations.benefitType.query.FindBenefitTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/benefitTypes")
public class BenefitTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BenefitTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BenefitType
	 * @return a List with the BenefitTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBenefitTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBenefitTypesBy query = new FindBenefitTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BenefitType> benefitTypes =((BenefitTypeFound) Scheduler.execute(query).data()).getBenefitTypes();

		if (benefitTypes.size() == 1) {
			return ResponseEntity.ok().body(benefitTypes.get(0));
		}

		return ResponseEntity.ok().body(benefitTypes);

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
	public ResponseEntity<Object> createBenefitType(HttpServletRequest request) throws Exception {

		BenefitType benefitTypeToBeAdded = new BenefitType();
		try {
			benefitTypeToBeAdded = BenefitTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBenefitType(benefitTypeToBeAdded);

	}

	/**
	 * creates a new BenefitType entry in the ofbiz database
	 * 
	 * @param benefitTypeToBeAdded
	 *            the BenefitType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBenefitType(@RequestBody BenefitType benefitTypeToBeAdded) throws Exception {

		AddBenefitType command = new AddBenefitType(benefitTypeToBeAdded);
		BenefitType benefitType = ((BenefitTypeAdded) Scheduler.execute(command).data()).getAddedBenefitType();
		
		if (benefitType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(benefitType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BenefitType could not be created.");
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
	public boolean updateBenefitType(HttpServletRequest request) throws Exception {

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

		BenefitType benefitTypeToBeUpdated = new BenefitType();

		try {
			benefitTypeToBeUpdated = BenefitTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBenefitType(benefitTypeToBeUpdated, benefitTypeToBeUpdated.getBenefitTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BenefitType with the specific Id
	 * 
	 * @param benefitTypeToBeUpdated
	 *            the BenefitType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{benefitTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBenefitType(@RequestBody BenefitType benefitTypeToBeUpdated,
			@PathVariable String benefitTypeId) throws Exception {

		benefitTypeToBeUpdated.setBenefitTypeId(benefitTypeId);

		UpdateBenefitType command = new UpdateBenefitType(benefitTypeToBeUpdated);

		try {
			if(((BenefitTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{benefitTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String benefitTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("benefitTypeId", benefitTypeId);
		try {

			Object foundBenefitType = findBenefitTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBenefitType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{benefitTypeId}")
	public ResponseEntity<Object> deleteBenefitTypeByIdUpdated(@PathVariable String benefitTypeId) throws Exception {
		DeleteBenefitType command = new DeleteBenefitType(benefitTypeId);

		try {
			if (((BenefitTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BenefitType could not be deleted");

	}

}
