package com.skytala.eCommerce.domain.product.relations.costComponent.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.AddCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.DeleteCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.UpdateCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.typeAttr.CostComponentTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.typeAttr.FindCostComponentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/costComponent/costComponentTypeAttrs")
public class CostComponentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponentTypeAttr
	 * @return a List with the CostComponentTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CostComponentTypeAttr>> findCostComponentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentTypeAttrsBy query = new FindCostComponentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentTypeAttr> costComponentTypeAttrs =((CostComponentTypeAttrFound) Scheduler.execute(query).data()).getCostComponentTypeAttrs();

		return ResponseEntity.ok().body(costComponentTypeAttrs);

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
	public ResponseEntity<CostComponentTypeAttr> createCostComponentTypeAttr(HttpServletRequest request) throws Exception {

		CostComponentTypeAttr costComponentTypeAttrToBeAdded = new CostComponentTypeAttr();
		try {
			costComponentTypeAttrToBeAdded = CostComponentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCostComponentTypeAttr(costComponentTypeAttrToBeAdded);

	}

	/**
	 * creates a new CostComponentTypeAttr entry in the ofbiz database
	 * 
	 * @param costComponentTypeAttrToBeAdded
	 *            the CostComponentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CostComponentTypeAttr> createCostComponentTypeAttr(@RequestBody CostComponentTypeAttr costComponentTypeAttrToBeAdded) throws Exception {

		AddCostComponentTypeAttr command = new AddCostComponentTypeAttr(costComponentTypeAttrToBeAdded);
		CostComponentTypeAttr costComponentTypeAttr = ((CostComponentTypeAttrAdded) Scheduler.execute(command).data()).getAddedCostComponentTypeAttr();
		
		if (costComponentTypeAttr != null) 
			return successful(costComponentTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CostComponentTypeAttr with the specific Id
	 * 
	 * @param costComponentTypeAttrToBeUpdated
	 *            the CostComponentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCostComponentTypeAttr(@RequestBody CostComponentTypeAttr costComponentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		costComponentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateCostComponentTypeAttr command = new UpdateCostComponentTypeAttr(costComponentTypeAttrToBeUpdated);

		try {
			if(((CostComponentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{costComponentTypeAttrId}")
	public ResponseEntity<CostComponentTypeAttr> findById(@PathVariable String costComponentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentTypeAttrId", costComponentTypeAttrId);
		try {

			List<CostComponentTypeAttr> foundCostComponentTypeAttr = findCostComponentTypeAttrsBy(requestParams).getBody();
			if(foundCostComponentTypeAttr.size()==1){				return successful(foundCostComponentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{costComponentTypeAttrId}")
	public ResponseEntity<String> deleteCostComponentTypeAttrByIdUpdated(@PathVariable String costComponentTypeAttrId) throws Exception {
		DeleteCostComponentTypeAttr command = new DeleteCostComponentTypeAttr(costComponentTypeAttrId);

		try {
			if (((CostComponentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
