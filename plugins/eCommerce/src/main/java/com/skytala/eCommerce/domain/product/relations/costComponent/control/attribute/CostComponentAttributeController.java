package com.skytala.eCommerce.domain.product.relations.costComponent.control.attribute;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.attribute.AddCostComponentAttribute;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.attribute.DeleteCostComponentAttribute;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.attribute.UpdateCostComponentAttribute;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.attribute.CostComponentAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.attribute.FindCostComponentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/costComponent/costComponentAttributes")
public class CostComponentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponentAttribute
	 * @return a List with the CostComponentAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCostComponentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentAttributesBy query = new FindCostComponentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentAttribute> costComponentAttributes =((CostComponentAttributeFound) Scheduler.execute(query).data()).getCostComponentAttributes();

		if (costComponentAttributes.size() == 1) {
			return ResponseEntity.ok().body(costComponentAttributes.get(0));
		}

		return ResponseEntity.ok().body(costComponentAttributes);

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
	public ResponseEntity<Object> createCostComponentAttribute(HttpServletRequest request) throws Exception {

		CostComponentAttribute costComponentAttributeToBeAdded = new CostComponentAttribute();
		try {
			costComponentAttributeToBeAdded = CostComponentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCostComponentAttribute(costComponentAttributeToBeAdded);

	}

	/**
	 * creates a new CostComponentAttribute entry in the ofbiz database
	 * 
	 * @param costComponentAttributeToBeAdded
	 *            the CostComponentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCostComponentAttribute(@RequestBody CostComponentAttribute costComponentAttributeToBeAdded) throws Exception {

		AddCostComponentAttribute command = new AddCostComponentAttribute(costComponentAttributeToBeAdded);
		CostComponentAttribute costComponentAttribute = ((CostComponentAttributeAdded) Scheduler.execute(command).data()).getAddedCostComponentAttribute();
		
		if (costComponentAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(costComponentAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CostComponentAttribute could not be created.");
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
	public boolean updateCostComponentAttribute(HttpServletRequest request) throws Exception {

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

		CostComponentAttribute costComponentAttributeToBeUpdated = new CostComponentAttribute();

		try {
			costComponentAttributeToBeUpdated = CostComponentAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCostComponentAttribute(costComponentAttributeToBeUpdated, costComponentAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CostComponentAttribute with the specific Id
	 * 
	 * @param costComponentAttributeToBeUpdated
	 *            the CostComponentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCostComponentAttribute(@RequestBody CostComponentAttribute costComponentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		costComponentAttributeToBeUpdated.setAttrName(attrName);

		UpdateCostComponentAttribute command = new UpdateCostComponentAttribute(costComponentAttributeToBeUpdated);

		try {
			if(((CostComponentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{costComponentAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String costComponentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentAttributeId", costComponentAttributeId);
		try {

			Object foundCostComponentAttribute = findCostComponentAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCostComponentAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{costComponentAttributeId}")
	public ResponseEntity<Object> deleteCostComponentAttributeByIdUpdated(@PathVariable String costComponentAttributeId) throws Exception {
		DeleteCostComponentAttribute command = new DeleteCostComponentAttribute(costComponentAttributeId);

		try {
			if (((CostComponentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CostComponentAttribute could not be deleted");

	}

}
