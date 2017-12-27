package com.skytala.eCommerce.domain.order.relations.custRequest.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.AddCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.DeleteCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.typeAttr.UpdateCustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.typeAttr.CustRequestTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.typeAttr.FindCustRequestTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestTypeAttrs")
public class CustRequestTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestTypeAttr
	 * @return a List with the CustRequestTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestTypeAttr>> findCustRequestTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestTypeAttrsBy query = new FindCustRequestTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestTypeAttr> custRequestTypeAttrs =((CustRequestTypeAttrFound) Scheduler.execute(query).data()).getCustRequestTypeAttrs();

		return ResponseEntity.ok().body(custRequestTypeAttrs);

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
	public ResponseEntity<CustRequestTypeAttr> createCustRequestTypeAttr(HttpServletRequest request) throws Exception {

		CustRequestTypeAttr custRequestTypeAttrToBeAdded = new CustRequestTypeAttr();
		try {
			custRequestTypeAttrToBeAdded = CustRequestTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCustRequestTypeAttr(custRequestTypeAttrToBeAdded);

	}

	/**
	 * creates a new CustRequestTypeAttr entry in the ofbiz database
	 * 
	 * @param custRequestTypeAttrToBeAdded
	 *            the CustRequestTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestTypeAttr> createCustRequestTypeAttr(@RequestBody CustRequestTypeAttr custRequestTypeAttrToBeAdded) throws Exception {

		AddCustRequestTypeAttr command = new AddCustRequestTypeAttr(custRequestTypeAttrToBeAdded);
		CustRequestTypeAttr custRequestTypeAttr = ((CustRequestTypeAttrAdded) Scheduler.execute(command).data()).getAddedCustRequestTypeAttr();
		
		if (custRequestTypeAttr != null) 
			return successful(custRequestTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestTypeAttr with the specific Id
	 * 
	 * @param custRequestTypeAttrToBeUpdated
	 *            the CustRequestTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestTypeAttr(@RequestBody CustRequestTypeAttr custRequestTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		custRequestTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateCustRequestTypeAttr command = new UpdateCustRequestTypeAttr(custRequestTypeAttrToBeUpdated);

		try {
			if(((CustRequestTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestTypeAttrId}")
	public ResponseEntity<CustRequestTypeAttr> findById(@PathVariable String custRequestTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestTypeAttrId", custRequestTypeAttrId);
		try {

			List<CustRequestTypeAttr> foundCustRequestTypeAttr = findCustRequestTypeAttrsBy(requestParams).getBody();
			if(foundCustRequestTypeAttr.size()==1){				return successful(foundCustRequestTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestTypeAttrId}")
	public ResponseEntity<String> deleteCustRequestTypeAttrByIdUpdated(@PathVariable String custRequestTypeAttrId) throws Exception {
		DeleteCustRequestTypeAttr command = new DeleteCustRequestTypeAttr(custRequestTypeAttrId);

		try {
			if (((CustRequestTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
