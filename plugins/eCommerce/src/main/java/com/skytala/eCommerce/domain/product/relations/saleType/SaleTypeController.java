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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findSaleTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSaleTypesBy query = new FindSaleTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SaleType> saleTypes =((SaleTypeFound) Scheduler.execute(query).data()).getSaleTypes();

		if (saleTypes.size() == 1) {
			return ResponseEntity.ok().body(saleTypes.get(0));
		}

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
	public ResponseEntity<Object> createSaleType(HttpServletRequest request) throws Exception {

		SaleType saleTypeToBeAdded = new SaleType();
		try {
			saleTypeToBeAdded = SaleTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSaleType(@RequestBody SaleType saleTypeToBeAdded) throws Exception {

		AddSaleType command = new AddSaleType(saleTypeToBeAdded);
		SaleType saleType = ((SaleTypeAdded) Scheduler.execute(command).data()).getAddedSaleType();
		
		if (saleType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(saleType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SaleType could not be created.");
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
	public boolean updateSaleType(HttpServletRequest request) throws Exception {

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

		SaleType saleTypeToBeUpdated = new SaleType();

		try {
			saleTypeToBeUpdated = SaleTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSaleType(saleTypeToBeUpdated, saleTypeToBeUpdated.getSaleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSaleType(@RequestBody SaleType saleTypeToBeUpdated,
			@PathVariable String saleTypeId) throws Exception {

		saleTypeToBeUpdated.setSaleTypeId(saleTypeId);

		UpdateSaleType command = new UpdateSaleType(saleTypeToBeUpdated);

		try {
			if(((SaleTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{saleTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String saleTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("saleTypeId", saleTypeId);
		try {

			Object foundSaleType = findSaleTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSaleType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{saleTypeId}")
	public ResponseEntity<Object> deleteSaleTypeByIdUpdated(@PathVariable String saleTypeId) throws Exception {
		DeleteSaleType command = new DeleteSaleType(saleTypeId);

		try {
			if (((SaleTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SaleType could not be deleted");

	}

}
