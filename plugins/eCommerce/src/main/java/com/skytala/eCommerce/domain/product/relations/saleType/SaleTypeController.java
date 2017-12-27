package com.skytala.eCommerce.domain.product.relations.saleType;

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
import com.skytala.eCommerce.domain.product.relations.saleType.command.AddSaleType;
import com.skytala.eCommerce.domain.product.relations.saleType.command.DeleteSaleType;
import com.skytala.eCommerce.domain.product.relations.saleType.command.UpdateSaleType;
import com.skytala.eCommerce.domain.product.relations.saleType.event.SaleTypeAdded;
import com.skytala.eCommerce.domain.product.relations.saleType.event.SaleTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.saleType.event.SaleTypeFound;
import com.skytala.eCommerce.domain.product.relations.saleType.event.SaleTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.saleType.mapper.SaleTypeMapper;
import com.skytala.eCommerce.domain.product.relations.saleType.model.SaleType;
import com.skytala.eCommerce.domain.product.relations.saleType.query.FindSaleTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/saleTypes")
public class SaleTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SaleTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SaleType
	 * @return a List with the SaleTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SaleType>> findSaleTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSaleTypesBy query = new FindSaleTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SaleType> saleTypes =((SaleTypeFound) Scheduler.execute(query).data()).getSaleTypes();

		return ResponseEntity.ok().body(saleTypes);

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
	public ResponseEntity<SaleType> createSaleType(HttpServletRequest request) throws Exception {

		SaleType saleTypeToBeAdded = new SaleType();
		try {
			saleTypeToBeAdded = SaleTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSaleType(saleTypeToBeAdded);

	}

	/**
	 * creates a new SaleType entry in the ofbiz database
	 * 
	 * @param saleTypeToBeAdded
	 *            the SaleType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SaleType> createSaleType(@RequestBody SaleType saleTypeToBeAdded) throws Exception {

		AddSaleType command = new AddSaleType(saleTypeToBeAdded);
		SaleType saleType = ((SaleTypeAdded) Scheduler.execute(command).data()).getAddedSaleType();
		
		if (saleType != null) 
			return successful(saleType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SaleType with the specific Id
	 * 
	 * @param saleTypeToBeUpdated
	 *            the SaleType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{saleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSaleType(@RequestBody SaleType saleTypeToBeUpdated,
			@PathVariable String saleTypeId) throws Exception {

		saleTypeToBeUpdated.setSaleTypeId(saleTypeId);

		UpdateSaleType command = new UpdateSaleType(saleTypeToBeUpdated);

		try {
			if(((SaleTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{saleTypeId}")
	public ResponseEntity<SaleType> findById(@PathVariable String saleTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("saleTypeId", saleTypeId);
		try {

			List<SaleType> foundSaleType = findSaleTypesBy(requestParams).getBody();
			if(foundSaleType.size()==1){				return successful(foundSaleType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{saleTypeId}")
	public ResponseEntity<String> deleteSaleTypeByIdUpdated(@PathVariable String saleTypeId) throws Exception {
		DeleteSaleType command = new DeleteSaleType(saleTypeId);

		try {
			if (((SaleTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
