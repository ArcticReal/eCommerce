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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<CostComponentAttribute>> findCostComponentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentAttributesBy query = new FindCostComponentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentAttribute> costComponentAttributes =((CostComponentAttributeFound) Scheduler.execute(query).data()).getCostComponentAttributes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CostComponentAttribute> createCostComponentAttribute(HttpServletRequest request) throws Exception {

		CostComponentAttribute costComponentAttributeToBeAdded = new CostComponentAttribute();
		try {
			costComponentAttributeToBeAdded = CostComponentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CostComponentAttribute> createCostComponentAttribute(@RequestBody CostComponentAttribute costComponentAttributeToBeAdded) throws Exception {

		AddCostComponentAttribute command = new AddCostComponentAttribute(costComponentAttributeToBeAdded);
		CostComponentAttribute costComponentAttribute = ((CostComponentAttributeAdded) Scheduler.execute(command).data()).getAddedCostComponentAttribute();
		
		if (costComponentAttribute != null) 
			return successful(costComponentAttribute);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCostComponentAttribute(@RequestBody CostComponentAttribute costComponentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		costComponentAttributeToBeUpdated.setAttrName(attrName);

		UpdateCostComponentAttribute command = new UpdateCostComponentAttribute(costComponentAttributeToBeUpdated);

		try {
			if(((CostComponentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{costComponentAttributeId}")
	public ResponseEntity<CostComponentAttribute> findById(@PathVariable String costComponentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentAttributeId", costComponentAttributeId);
		try {

			List<CostComponentAttribute> foundCostComponentAttribute = findCostComponentAttributesBy(requestParams).getBody();
			if(foundCostComponentAttribute.size()==1){				return successful(foundCostComponentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{costComponentAttributeId}")
	public ResponseEntity<String> deleteCostComponentAttributeByIdUpdated(@PathVariable String costComponentAttributeId) throws Exception {
		DeleteCostComponentAttribute command = new DeleteCostComponentAttribute(costComponentAttributeId);

		try {
			if (((CostComponentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
