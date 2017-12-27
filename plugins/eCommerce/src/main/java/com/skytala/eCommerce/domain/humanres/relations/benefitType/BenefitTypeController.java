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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/benefitTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<BenefitType>> findBenefitTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBenefitTypesBy query = new FindBenefitTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BenefitType> benefitTypes =((BenefitTypeFound) Scheduler.execute(query).data()).getBenefitTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<BenefitType> createBenefitType(HttpServletRequest request) throws Exception {

		BenefitType benefitTypeToBeAdded = new BenefitType();
		try {
			benefitTypeToBeAdded = BenefitTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<BenefitType> createBenefitType(@RequestBody BenefitType benefitTypeToBeAdded) throws Exception {

		AddBenefitType command = new AddBenefitType(benefitTypeToBeAdded);
		BenefitType benefitType = ((BenefitTypeAdded) Scheduler.execute(command).data()).getAddedBenefitType();
		
		if (benefitType != null) 
			return successful(benefitType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateBenefitType(@RequestBody BenefitType benefitTypeToBeUpdated,
			@PathVariable String benefitTypeId) throws Exception {

		benefitTypeToBeUpdated.setBenefitTypeId(benefitTypeId);

		UpdateBenefitType command = new UpdateBenefitType(benefitTypeToBeUpdated);

		try {
			if(((BenefitTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{benefitTypeId}")
	public ResponseEntity<BenefitType> findById(@PathVariable String benefitTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("benefitTypeId", benefitTypeId);
		try {

			List<BenefitType> foundBenefitType = findBenefitTypesBy(requestParams).getBody();
			if(foundBenefitType.size()==1){				return successful(foundBenefitType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{benefitTypeId}")
	public ResponseEntity<String> deleteBenefitTypeByIdUpdated(@PathVariable String benefitTypeId) throws Exception {
		DeleteBenefitType command = new DeleteBenefitType(benefitTypeId);

		try {
			if (((BenefitTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
